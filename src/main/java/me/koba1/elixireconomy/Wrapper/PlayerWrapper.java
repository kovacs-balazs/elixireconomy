package me.koba1.elixireconomy.Wrapper;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerWrapper {

    public static void removeItem(Player p, int amount) {
        ItemStack is = p.getItemInHand();
        is.setAmount(Math.max((is.getAmount() - amount), 0));
        p.setItemInHand(is);
    }
}
