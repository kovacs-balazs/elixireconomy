package me.koba1.elixireconomy.Ballon;

import me.koba1.elixireconomy.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class LoadChunkEvent implements Listener {

    /**
      NINCSEN LOADOLVA AZ EVENT!!!!!!!!!
     */


    private Main m = Main.getPlugin(Main.class);

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent e) {
        System.out.println("YEEEEEEEEE");
        if(e.getChunk().getX() == m.getLocationOfArmorStand().getChunk().getX()) {
            System.out.println("NOOOOOOOOOOOOOO");
            Location locSaved = m.getLocationOfArmorStand();
            Location locSaved2 = m.getLocationOfArmorStand();
            Location armorStand = m.getLocationOfArmorStand();

            ArmorStand as1 = null;
            ArmorStand as2 = null;

            for (ArmorStand ent : armorStand.getNearbyEntitiesByType(ArmorStand.class, 4)) {
                System.out.println(ent);
                if (ent.isSilent()) {
                    if (ent.getCustomName().equalsIgnoreCase("top")) {
                        ent.teleport(armorStand.add(0, 3.38, 0));
                        as2 = ent;
                        armorStand.add(0, -3.38, 0);
                    }
                    if (ent.getCustomName().equalsIgnoreCase("bottom")) {
                        ent.teleport(armorStand.add(0, 1, 0));
                        as1 = ent;
                        armorStand.add(0, -1, 0);
                    }
                }
            }

            if (as1 == null) {
                Bukkit.getLogger().severe("Spawn balloon nincs letéve!");
                as1 = BalloonCommand.placeBottom(locSaved);
            }
            if (as2 == null) {
                Bukkit.getLogger().severe("Spawn balloon2 nincs letéve!");
                as2 = BalloonCommand.placeTop(locSaved2);
            }

            MoveUtil.startMoving(as1, as2);
        }
    }
}
