package me.koba1.elixireconomy.NoteBlocks;

import me.koba1.elixireconomy.Files.File_NoteBlock;
import me.koba1.elixireconomy.Wrapper.NoteBlock.NoteBlockWrapper;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Block_Note {

    public static ItemStack getItemFromBlock(Block block) {
        String string = NoteBlockWrapper.getItemName(block);

        if(string == null) {
            return new ItemStack(Material.NOTE_BLOCK);
        }

        ItemStack is = new ItemStack(Material.valueOf(File_NoteBlock.get().getString(string + ".item.material")));
        ItemMeta im = is.getItemMeta();

        im.setDisplayName(File_NoteBlock.get().getString(string + ".item.name"));
        im.setCustomModelData(File_NoteBlock.get().getInt(string + ".item.custommodeldata"));

        is.setItemMeta(im);
        return is;
    }
}
