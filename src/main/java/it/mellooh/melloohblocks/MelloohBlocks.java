package it.mellooh.melloohblocks;

import it.mellooh.melloohblocks.listeners.LobbyBlockListener;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public final class MelloohBlocks extends JavaPlugin {

    @Getter private Material blockMaterial;
    @Getter private int slot;
    @Getter private int amount;
    @Getter private String displayName;
    @Getter private boolean lockMove;
    private boolean pluginEnabled;
    @Getter private final Set<Block> placedBlocks = new HashSet<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfigValues();

        getServer().getPluginManager().registerEvents(
                new LobbyBlockListener(this), this
        );
        getLogger().info("\n" +
                "  __  __      _ _             _     ____  _            _         \n" +
                " |  \\/  |    | | |           | |   |  _ \\| |          | |        \n" +
                " | \\  / | ___| | | ___   ___ | |__ | |_) | | ___   ___| | _____  \n" +
                " | |\\/| |/ _ \\ | |/ _ \\ / _ \\| '_ \\|  _ <| |/ _ \\ / __| |/ / __| \n" +
                " | |  | |  __/ | | (_) | (_) | | | | |_) | | (_) | (__|   <\\__ \\ \n" +
                " |_|  |_|\\___|_|_|\\___/ \\___/|_| |_|____/|_|\\___/ \\___|_|\\_\\___/ \n" +
                "                                                                 \n" +
                " > Plugin: MelloohBlocks\n" +
                " > Version: " + getDescription().getVersion() + "\n" +
                " > Author: Mellooh\n" +
                " > Status: ENABLED \n"
        );
    }

    public void loadConfigValues() {
        FileConfiguration config = getConfig();
        pluginEnabled = config.getBoolean("enabled");
        blockMaterial = Material.matchMaterial(config.getString("block", "DIAMOND_BLOCK"));
        slot = config.getInt("slot", 4);
        amount = config.getInt("amount", 64);
        displayName = config.getString("display-name", "&bBlocchi Infiniti");
        lockMove = config.getBoolean("lock-move", true);
    }

    public boolean isPluginEnabled() {
        return pluginEnabled;
    }

    @Override
    public void onDisable() {
        int removed = 0;
        for (Block block : placedBlocks) {
            if (block.getType() == blockMaterial) {
                block.setType(Material.AIR);
                removed++;
            }
        }
        placedBlocks.clear();

        getLogger().info("MelloohBlocks has been disabled.");
        getLogger().info("Removed " + removed + " placed blocks.");
    }
}
