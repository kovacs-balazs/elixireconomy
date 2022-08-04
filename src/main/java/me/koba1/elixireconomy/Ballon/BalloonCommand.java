package me.koba1.elixireconomy.Ballon;

import me.koba1.elixireconomy.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class BalloonCommand implements CommandExecutor {

    private Main m = Main.getPlugin(Main.class);
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(cmd.getName().equalsIgnoreCase("setballoon")) {
            if(sender instanceof Player p) {
                if (!p.isOp()) return false;

                Location playerLoc = p.getLocation();
                playerLoc.setYaw(180);
                playerLoc.setPitch(0);

                m.getConfig().set("spawn-location",
                        playerLoc.getBlockX() + " "
                                + playerLoc.getBlockY() + " "
                                + playerLoc.getBlockZ() + " "
                                + playerLoc.getYaw() + " 0");
                m.saveConfig();

                MoveUtil.startMoving( placeBottom(playerLoc), placeTop(playerLoc));
            }
        }
        return false;
    }

    public static ArmorStand placeBottom(Location loc) {
        ArmorStand bottomArmorStand = setupStand((ArmorStand) loc.getWorld().spawnEntity(loc.add(0, 1, 0), EntityType.ARMOR_STAND));
        bottomArmorStand.setCustomName("bottom");
        bottomArmorStand.setHelmet(bottomAirBalloon());
        loc.add(0, -1, 0);
        return bottomArmorStand;
    }

    public static ArmorStand placeTop(Location loc) {
        ArmorStand topArmorStand = setupStand((ArmorStand) loc.getWorld().spawnEntity(loc.add(0, 3.38, 0), EntityType.ARMOR_STAND));
        topArmorStand.setCustomName("top");
        topArmorStand.setHelmet(topAirBalloon());
        loc.add(0, -3.38, 0);
        return topArmorStand;
    }

    public static ArmorStand setupStand(ArmorStand as) {
        as.setCustomNameVisible(false);
        as.setVisible(true);
        as.setSilent(true);
        as.setAI(false);
        as.setPersistent(false);
        as.setVisible(false);
        as.setInvulnerable(true);
        as.setGravity(false);
        return as;
    }

    public static ItemStack bottomAirBalloon() {
        ItemStack is = new ItemStack(Material.PAPER);
        ItemMeta im = is.getItemMeta();
        im.setCustomModelData(2155);
        is.setItemMeta(im);
        return is;
    }

    public static ItemStack topAirBalloon() {
        ItemStack is = new ItemStack(Material.PAPER);
        ItemMeta im = is.getItemMeta();
        im.setCustomModelData(2153);
        is.setItemMeta(im);
        return is;
    }
}
