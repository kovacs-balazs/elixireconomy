package me.koba1.elixireconomy.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

public class PhysicEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPhysics(BlockPhysicsEvent event) {
        Block aboveBlock = event.getBlock().getLocation().add(0, 1, 0).getBlock();
        if (aboveBlock.getType() == Material.NOTE_BLOCK) {
            updateAndCheck(event.getBlock().getLocation());
            event.setCancelled(true);
        }
        if (event.getBlock().getType() == Material.NOTE_BLOCK)
            event.setCancelled(true);
        if (event.getBlock().getType().toString().toLowerCase().contains("sign"))
            return;
        event.getBlock().getState().update(true, false);

    }

    public void updateAndCheck(Location loc) {
        Block b = loc.add(0, 1, 0).getBlock();
        if (b.getType() == Material.NOTE_BLOCK)
            b.getState().update(true, true);

        Location nextBlock = b.getLocation().add(0, 1, 0);
        if (nextBlock.getBlock().getType() == Material.NOTE_BLOCK)
            updateAndCheck(b.getLocation());
    }
}
