package me.koba1.elixireconomy.ItemFrameItems;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.ItemStack;

public class ItemFrameUtil {

    public static void PlaceItemFrame(Location loc, ItemStack is, boolean fixed) {
        ItemFrame frame = (ItemFrame) loc.getWorld().spawnEntity(loc, EntityType.ITEM_FRAME);
        frame.setVisible(false);
        frame.setItem(is);
        frame.setFixed(fixed);
        frame.setFacingDirection(BlockFace.UP, true);
    }
}
