package it.mellooh.melloohblocks.listeners;

import it.mellooh.melloohblocks.MelloohBlocks;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyBlockListener implements Listener {

    private final MelloohBlocks plugin;

    public LobbyBlockListener(MelloohBlocks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!plugin.isPluginEnabled() || plugin.getBlockMaterial() == null) return;

        Player player = event.getPlayer();
        PlayerInventory inv = player.getInventory();
        inv.setItem(plugin.getSlot(), melloohBlocksItem());
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!plugin.isPluginEnabled() || plugin.getBlockMaterial() == null) return;

        if (event.getBlockPlaced().getType() != plugin.getBlockMaterial()) return;

        Player player = event.getPlayer();
        PlayerInventory inv = player.getInventory();

        inv.setItem(plugin.getSlot(), melloohBlocksItem());

        Block block = event.getBlockPlaced();
        plugin.getPlacedBlocks().add(block);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (block.getType() == plugin.getBlockMaterial()) {
                    block.setType(Material.AIR);
                    plugin.getPlacedBlocks().remove(block);
                }
            }
        }.runTaskLater(plugin, 20L * 5);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!plugin.isPluginEnabled() || !plugin.isLockMove()) return;

        if (!(event.getWhoClicked() instanceof Player)) return;

        ItemStack current = event.getCurrentItem();
        if (current == null || current.getType() != plugin.getBlockMaterial()) return;

        if (current.hasItemMeta() && current.getItemMeta().hasDisplayName()) {
            String displayName = current.getItemMeta().getDisplayName();
            if (displayName.equals(plugin.getDisplayName().replace("&", "§"))) {
                event.setCancelled(true);
            }
        }
    }

    private ItemStack melloohBlocksItem() {
        ItemStack item = new ItemStack(plugin.getBlockMaterial(), plugin.getAmount());
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(plugin.getDisplayName().replace("&", "§"));
            item.setItemMeta(meta);
        }
        return item;
    }
}
