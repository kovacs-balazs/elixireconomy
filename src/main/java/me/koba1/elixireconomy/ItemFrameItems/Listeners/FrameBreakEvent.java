package me.koba1.elixireconomy.ItemFrameItems.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakEvent;

public class FrameBreakEvent implements Listener {

    @EventHandler
    public void onBreak(HangingBreakEvent e) {
        if (e.getEntity() instanceof ItemFrame) {
            ItemFrame frame = (ItemFrame) e.getEntity();

            if(frame.getItem().getType() == Material.AIR) return;
            if (!frame.getItem().hasItemMeta()) return;

            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), frame.getItem());
            e.getEntity().remove();
        }
    }

//    public static boolean hasItemFrame(Block block) {
//        Iterator<ItemFrame> entities = block.getLocation().getNearbyEntitiesByType(ItemFrame.class, 1).iterator();
//
//        while(entities.hasNext()) {
//            ItemFrame frame = entities.next();
//            if(frame.getLocation().getBlock().getRelative(frame.getAttachedFace()).equals(block)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
}
