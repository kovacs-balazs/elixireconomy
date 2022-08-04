package me.koba1.elixireconomy;

import me.koba1.elixireconomy.Ballon.BalloonCommand;
import me.koba1.elixireconomy.Ballon.LoadChunkEvent;
import me.koba1.elixireconomy.Ballon.MoveUtil;
import me.koba1.elixireconomy.Ballon.SpawnBalloon;
import me.koba1.elixireconomy.Files.File_ItemFrames;
import me.koba1.elixireconomy.Files.File_NoteBlock;
import me.koba1.elixireconomy.ItemFrameItems.Listeners.FrameBreakEvent;
import me.koba1.elixireconomy.ItemFrameItems.Listeners.FrameInteractEvent;
import me.koba1.elixireconomy.ItemFrameItems.Listeners.FrameRemoveItemEvent;
import me.koba1.elixireconomy.Listeners.PaperPlaceEvent;
import me.koba1.elixireconomy.Listeners.PhysicEvent;
import me.koba1.elixireconomy.Listeners.PlayNoteEvent;
import me.koba1.elixireconomy.NoteBlocks.NoteBlockBreak;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventPriority;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public final class Main extends JavaPlugin {

    ArrayList<String> cancelBlockPlace;

    @Override
    public void onEnable() {
        // Plugin startup logic

        cancelBlockPlace = new ArrayList<>();
        //File_NoteBlock.setup();

        if (!new File(getDataFolder(), "noteblock.yml").exists()) {
            saveResource("noteblock.yml", false);
        }

        if (!new File(getDataFolder(), "itemframe_items.yml").exists()) {
            saveResource("itemframe_items.yml", false);
        }

        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveResource("config.yml", false);
        }

        File_ItemFrames.setup();
        File_NoteBlock.setup();

        registerEvents();

        getCommand("setballoon").setExecutor(new BalloonCommand());

        String[] coords = getConfig().getString("spawn-location").split(" ");

        Location armorStand = new Location(
                Bukkit.getWorld(getConfig().getString("spawn-world-name")),
                Integer.parseInt(coords[0]),
                Integer.parseInt(coords[1]),
                Integer.parseInt(coords[2]),
                Float.parseFloat(coords[3]),
                Float.parseFloat(coords[4])
        );

        armorStand.add(0.5, 0, 0.5);

        SpawnBalloon.spawnBalloon();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new NoteBlockBreak(), this);
        pm.registerEvents(new PaperPlaceEvent(), this);
        pm.registerEvents(new PlayNoteEvent(), this);
        pm.registerEvents(new PhysicEvent(), this);
        pm.registerEvents(new FrameInteractEvent(), this);
        pm.registerEvents(new FrameBreakEvent(), this);
        pm.registerEvents(new FrameRemoveItemEvent(), this);
        //pm.registerEvents(new LoadChunkEvent(), this);
    }

    public Location getLocationOfArmorStand() {
        String[] coords = getConfig().getString("spawn-location").split(" ");

        Location armorStand = new Location(
                Bukkit.getWorld(getConfig().getString("spawn-world-name")),
                Integer.parseInt(coords[0]),
                Integer.parseInt(coords[1]),
                Integer.parseInt(coords[2]),
                Float.parseFloat(coords[3]),
                Float.parseFloat(coords[4])
        );
        return armorStand.add(0.5, 0, 0.5);
    }

    public static boolean isInteractable(Block block) {
        Material type = block.getType();
        boolean interactable = type.isInteractable();
        if (!interactable)
            return false;

        switch (type) {
            case ACACIA_STAIRS:
            case ANDESITE_STAIRS:
            case BIRCH_STAIRS:
            case BLACKSTONE_STAIRS:
            case BRICK_STAIRS:
            case COBBLESTONE_STAIRS:
            case CRIMSON_STAIRS:
            case DARK_OAK_STAIRS:
            case DARK_PRISMARINE_STAIRS:
            case DIORITE_STAIRS:
            case END_STONE_BRICK_STAIRS:
            case GRANITE_STAIRS:
            case JUNGLE_STAIRS:
            case MOSSY_COBBLESTONE_STAIRS:
            case MOSSY_STONE_BRICK_STAIRS:
            case NETHER_BRICK_STAIRS:
            case OAK_STAIRS:
            case POLISHED_ANDESITE_STAIRS:
            case POLISHED_BLACKSTONE_BRICK_STAIRS:
            case POLISHED_BLACKSTONE_STAIRS:
            case POLISHED_DIORITE_STAIRS:
            case POLISHED_GRANITE_STAIRS:
            case PRISMARINE_BRICK_STAIRS:
            case PRISMARINE_STAIRS:
            case PURPUR_STAIRS:
            case QUARTZ_STAIRS:
            case RED_NETHER_BRICK_STAIRS:
            case RED_SANDSTONE_STAIRS:
            case SANDSTONE_STAIRS:
            case SMOOTH_QUARTZ_STAIRS:
            case SMOOTH_RED_SANDSTONE_STAIRS:
            case SMOOTH_SANDSTONE_STAIRS:
            case SPRUCE_STAIRS:
            case STONE_BRICK_STAIRS:
            case STONE_STAIRS:
            case WARPED_STAIRS:

            case ACACIA_FENCE:
            case BIRCH_FENCE:
            case CRIMSON_FENCE:
            case DARK_OAK_FENCE:
            case JUNGLE_FENCE:
            case MOVING_PISTON:
            case NETHER_BRICK_FENCE:
            case OAK_FENCE:
            case PUMPKIN:
            case REDSTONE_ORE:
            case REDSTONE_WIRE:
            case SPRUCE_FENCE:
            case WARPED_FENCE:
                return false;
            default:
                return true;
        }
    }
}
