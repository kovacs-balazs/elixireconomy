package me.koba1.elixireconomy.ItemFrameItems.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class FrameRemoveItemEvent implements Listener {

    @EventHandler
    public void FrameEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof ItemFrame frame) {
            if (e.getDamager() instanceof Player) {
                if(e.isCancelled()) return;

                if(frame.getItem().getType() == Material.AIR) return;
                if(!frame.getItem().hasItemMeta()) return;

                e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), frame.getItem());
                e.getEntity().remove();
            }
        }
    }
}
