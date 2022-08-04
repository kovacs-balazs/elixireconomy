package me.koba1.elixireconomy.NoteBlocks;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class NoteBlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(e.getBlock().getType() == Material.NOTE_BLOCK) {
            e.setDropItems(false);
            e.setExpToDrop(0);
            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), Block_Note.getItemFromBlock(e.getBlock()));
        }
    }
}
