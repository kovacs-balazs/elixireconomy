package me.koba1.elixireconomy.Wrapper.NoteBlock;

import me.koba1.elixireconomy.Files.File_NoteBlock;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.NoteBlock;

public class NoteBlockWrapper {

    public static String getItemName(Block block) {
        if(!(block.getBlockData() instanceof NoteBlock note)) return null;

        for(String string : File_NoteBlock.get().getKeys(false)) {
            if((File_NoteBlock.get().getInt(string + ".noteblock.note") == note.getNote().getId())
                    && File_NoteBlock.get().getString(string + ".noteblock.instrument").equalsIgnoreCase(note.getInstrument().name())) {
                return string;
            } else if(File_NoteBlock.get().getStringList(string + ".noteblock.facing-notes").contains(String.valueOf( note.getNote().getId()))
                    && File_NoteBlock.get().getString(string + ".noteblock.instrument").equalsIgnoreCase(note.getInstrument().name())) {
                return string;
            }
        }

        return null;
    }
}
