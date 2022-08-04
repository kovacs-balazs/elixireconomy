package me.koba1.elixireconomy.ItemFrameItems.ConfigWrapper;

import me.koba1.elixireconomy.Files.File_ItemFrames;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class FrameReader {

    public static HashMap<String, String> getDataFromItem(ItemStack is) {
        if(is == null) return new HashMap<>();
        if(!is.hasItemMeta()) return new HashMap<>();

        FileConfiguration config = File_ItemFrames.get();
        ItemMeta im = is.getItemMeta();

        for(String string : config.getKeys(false)) {
            if(config.getString(string + ".custommodeldata").equals(String.valueOf(im.getCustomModelData())) && config.getString(string + ".material").equals(is.getType().name())) {
                return new HashMap<>() {{
                    put("material", is.getType().name());
                    put("custommodeldata", String.valueOf(im.getCustomModelData()));
                    put("confirm-remove", config.getString(string + ".confirm-remove"));
                }};
            }
        }

        return new HashMap<>();
    }
}
