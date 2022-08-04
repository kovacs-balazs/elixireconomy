package me.koba1.elixireconomy.ItemFrameItems.Listeners;

import me.koba1.elixireconomy.ItemFrameItems.ConfigWrapper.FrameReader;
import me.koba1.elixireconomy.ItemFrameItems.ItemFrameUtil;
import me.koba1.elixireconomy.Wrapper.PlayerWrapper;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;

public class FrameInteractEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) return;

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        if (!(e.getHand().equals(EquipmentSlot.HAND))) return;

        if (e.getItem() == null)
            return;

        if(e.getItem().hasItemMeta()) {
            HashMap<String, String> data = FrameReader.getDataFromItem(e.getItem());
            if(data.isEmpty()) return;

            if(e.getClickedBlock().getType() == Material.AIR) return;
            if(e.getClickedBlock().getLocation().add(0, 1, 0).getBlock().getType() != Material.AIR) return;

            ItemFrameUtil.PlaceItemFrame(e.getClickedBlock().getLocation().add(0, 1, 0), e.getItem(), false);
            PlayerWrapper.removeItem(e.getPlayer(), 1);
        }
    }
}
