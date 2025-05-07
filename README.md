# 🧱 MelloohBlocks
Gives players unlimited temporary blocks to place in the lobby. Perfect for creative hubs, interactive spawns, or just for fun building areas.

# 📦 Installation
Download the .jar file from the Releases section

Place it into your server's plugins folder

Restart the server or use /reload

# ⚙️ Configuration (config.yml)
```yaml
############################################################
#                                                          #
#                MELLOOHBLOCKS - CONFIGURATION             #
#                                                          #
#        Plugin created by Mellooh - For lobby use         #
# Gives players infinite temporary blocks to place in lobby#
#                                                          #
############################################################


# Enable or disable the plugin.
# If false, all features will be disabled and players will not receive blocks.
enabled: true

# The type of block to give to the player.
# Must be a valid Spigot/Bukkit material name (e.g., DIAMOND_BLOCK, STONE, GOLD_BLOCK).
block: DIAMOND_BLOCK

# The inventory slot where the item will appear.
# Values range from 0 (first slot) to 8 (last hotbar slot).
slot: 7

# The amount of blocks shown in the item stack.
# This is only visual and does not limit the number of blocks a player can place.
amount: 64

# The custom display name for the block item.
# Supports Minecraft color codes using '&' (e.g., &b = light blue, &c = red).
# This name is used to identify the item and can be used for protection.
display-name: "&bInfinite Blocks"

# Prevents players from moving the block item inside their inventory.
# If true, the item cannot be dragged, moved, or placed in other slots.
lock-move: true
```

# 👤 Author
Plugin developed by Mellooh
📧 For support: discord.gg/mellooh_

# 📄 License
This plugin is released under the MIT License.
Feel free to modify or redistribute it — just keep credit where it's due!

