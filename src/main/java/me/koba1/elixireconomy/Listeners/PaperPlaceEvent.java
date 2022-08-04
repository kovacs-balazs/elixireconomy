package me.koba1.elixireconomy.Listeners;

import me.koba1.elixireconomy.Files.File_NoteBlock;
import me.koba1.elixireconomy.Main;
import me.koba1.elixireconomy.Wrapper.NoteBlock.ItemSearcher;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class PaperPlaceEvent implements Listener {

    @EventHandler
    public void onPaperPlace(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) return;

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        if (!(e.getHand().equals(EquipmentSlot.HAND))) return;

        if (e.getClickedBlock().getType() == Material.NOTE_BLOCK) {
            if (!e.getPlayer().isOp())
                e.setCancelled(true);
        }

        if (e.getItem() == null)
            return;

        String config = ItemSearcher.getBlockFromItem(e.getItem());

        if (config != null) {

            if (Main.isInteractable(e.getClickedBlock())) {
                if(e.getClickedBlock().getType() != Material.NOTE_BLOCK) {
                    if (!e.getPlayer().isSneaking())
                        return;
                }
            }

            HashMap<String, String> hashMap = ItemSearcher.getDatas(config);

            Block face = e.getClickedBlock().getRelative(e.getBlockFace());

            if (face.getType() == Material.AIR) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        e.setCancelled(true);

                        face.setType(Material.NOTE_BLOCK);

                        NoteBlock note = (NoteBlock) face.getBlockData();

                        Note note2 = new Note(File_NoteBlock.get().getInt(config + ".noteblock.note"));

                        if (hashMap.get("allow-facing").equals("true")) {
                            if (getFacing(e.getClickedBlock(), face).equals(BlockFace.UP))
                                note2 = new Note(File_NoteBlock.get().getInt(config + ".noteblock.note"));
                            else if (getFacing(e.getClickedBlock(), face).equals(BlockFace.NORTH))
                                note2 = new Note(File_NoteBlock.get().getInt(config + ".noteblock.note") + 1);
                            else if (getFacing(e.getClickedBlock(), face).equals(BlockFace.WEST))
                                note2 = new Note(File_NoteBlock.get().getInt(config + ".noteblock.note") + 2);
                        }

                        note.setNote(note2);
                        note.setPowered(false);

                        note.setInstrument(Instrument.valueOf(File_NoteBlock.get().getString(config + ".noteblock.instrument").toUpperCase()));

                        face.setBlockData(note);

                        if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                            ItemStack is = e.getPlayer().getItemInHand();
                            is.setAmount(is.getAmount() - 1);
                            e.getPlayer().setItemInHand(is);
                        }
                        return;
                    }
                }.runTaskLater(Main.getPlugin(Main.class), 1L);
            }
        }
    }

    public BlockFace getFacing(Block against, Block noteBlock) {
        if(against.getRelative(BlockFace.UP).equals(noteBlock))
            return BlockFace.UP;
        if(against.getRelative(BlockFace.DOWN).equals(noteBlock))
            return BlockFace.UP;
        if(against.getRelative(BlockFace.NORTH).equals(noteBlock))
            return BlockFace.NORTH;
        if(against.getRelative(BlockFace.SOUTH).equals(noteBlock))
            return BlockFace.NORTH;
        if(against.getRelative(BlockFace.WEST).equals(noteBlock))
            return BlockFace.WEST;
        if(against.getRelative(BlockFace.EAST).equals(noteBlock))
            return BlockFace.WEST;

        return BlockFace.UP;
    }
}
