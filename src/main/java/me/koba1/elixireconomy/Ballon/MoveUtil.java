package me.koba1.elixireconomy.Ballon;

import me.koba1.elixireconomy.Main;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class MoveUtil {

    public static void startMoving(ArmorStand as1, ArmorStand as2) {
        as1.setHeadPose(new EulerAngle(0, 0, 0));
        as2.setHeadPose(new EulerAngle(0, 0, 0));
        new BukkitRunnable() {
            int counter = 0;
            int i = 0;
            
            Location as1Loc = as1.getLocation();
            Location as2Loc = as2.getLocation();
            
            @Override
            public void run() {
                ///////////////////// LE FEL MOZOG
                if(counter == 0) {
                    if (i <= 10) {
                        as1Loc.add(0, 0.02, 0);
                        as2Loc.add(0, 0.02, 0);
                        i++;
                    } else {
                        i = 0;
                        counter++;
                    }
                }
                //
                else if(counter == 1) {
                    if (i <= 10) {
                        as1Loc.add(0, -0.02, 0);
                        as2Loc.add(0, -0.02, 0);
                        i++;
                    } else {
                        i = 0;
                        counter = 0;
                    }
                }
                /*/////////////////////// X OLDALRA MOZOG
                else if(counter == 2) {
                    if (i <= 10) {
                        if(true) {
                            as1Loc.add(0.01, 0.02, 0);
                            as2Loc.add(0.01, 0.02, 0);
                        }
                        i++;
                    } else {
                        i = 0;
                        counter++;
                    }
                }
                //
                else if(counter == 3) {
                    if (i <= 10) {
                        if(true) {
                            as1Loc.add(-0.01, -0.02, 0);
                            as2Loc.add(-0.01, -0.02, 0);
                        }
                        i++;
                    } else {
                        i = 0;
                        counter++;
                    }
                }
                //////////////////// Z OLDALRA MOZOG
                else if(counter == 4) {
                    if (i <= 10) {
                        if(true) {
                            as1Loc.add(0, 0.02, 0.01);
                            as2Loc.add(0, 0.02, 0.01);
                        }
                        i++;
                    } else {
                        i = 0;
                        counter++;
                    }
                }
                //
                else if(counter == 5) {
                    if (i <= 10) {
                        if(true) {
                            as1Loc.add(0, -0.02, -0.01);
                            as2Loc.add(0, -0.02, -0.01);
                        }
                        i++;
                    } else {
                        i = 0;
                        counter = 0;
                    }
                }*/
                // 6 7 8 9 10
                if(i > 5) {
                    as1.setHeadPose(as1.getHeadPose().add(0, 0, 0.0025));
                }
                // 0 1 2 3 4 5
                else if(i <= 5) {
                    as1.setHeadPose(as1.getHeadPose().add(0, 0, -0.0025));
                } else {
                    as1.setHeadPose(as1.getHeadPose().add(0, 0, 0.0025));
                }
                as1.teleport(as1Loc);
                as2.teleport(as2Loc);
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0L, 4L);
    }
}
