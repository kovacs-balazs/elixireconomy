package me.koba1.elixireconomy.Ballon;

import me.koba1.elixireconomy.Main;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

public class SpawnBalloon {

    private static Main m = Main.getPlugin(Main.class);

    public static void spawnBalloon() {
        Location loc = m.getLocationOfArmorStand();

        if(!loc.getChunk().isLoaded()) {
            loc.getChunk().load();
        }

        for(ArmorStand stand : loc.getNearbyEntitiesByType(ArmorStand.class, 4)) {
            stand.remove();
        }

        MoveUtil.startMoving(BalloonCommand.placeBottom(loc), BalloonCommand.placeTop(loc));
    }
}
