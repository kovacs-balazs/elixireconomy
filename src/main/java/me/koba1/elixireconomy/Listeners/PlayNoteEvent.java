package me.koba1.elixireconomy.Listeners;

import org.bukkit.Instrument;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.NotePlayEvent;

public class PlayNoteEvent implements Listener {

    @EventHandler
    public void onNotePlay(NotePlayEvent e) {
        BlockData data = e.getBlock().getBlockData();
        NoteBlock noteBlock = (NoteBlock) data;
        noteBlock.setInstrument(e.getInstrument());
        e.getBlock().setBlockData(data);
        e.getBlock().getState().update();
    }
}
