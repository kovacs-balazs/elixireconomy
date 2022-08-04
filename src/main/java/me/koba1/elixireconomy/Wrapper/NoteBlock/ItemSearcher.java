package me.koba1.elixireconomy.Wrapper.NoteBlock;

import me.koba1.elixireconomy.Files.File_NoteBlock;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ItemSearcher {

    public static String getBlockFromItem(ItemStack is) {
        if(is == null) return null;
        if(!is.hasItemMeta()) return null;
        ItemMeta im = is.getItemMeta();
        for(String str : File_NoteBlock.get().getKeys(false)) {
            HashMap<String, String> hashMap = getDatas(str);
            //if(im.getDisplayName().equalsIgnoreCase(hashMap.get("name"))) {
            if (im.getCustomModelData() == Integer.parseInt(hashMap.get("custommodeldata"))) {
                if (is.getType() == Material.valueOf(hashMap.get("material"))) {
                    return str;
                }
            }
        }

        return null;
    }

    public static HashMap<String, String> getDatas(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("configsection", str);
        hashMap.put("name", File_NoteBlock.get().getString(str + ".item.name"));
        hashMap.put("material", File_NoteBlock.get().getString(str + ".item.material"));
        hashMap.put("custommodeldata", File_NoteBlock.get().getString(str + ".item.custommodeldata"));
        hashMap.put("allow-facing", File_NoteBlock.get().getString(str + ".allow-facing"));
        return hashMap;
    }
}
