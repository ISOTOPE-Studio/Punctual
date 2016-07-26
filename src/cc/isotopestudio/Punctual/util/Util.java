package cc.isotopestudio.Punctual.util;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;

import static cc.isotopestudio.Punctual.Punctual.plugin;

/**
 * Created by Mars on 7/26/2016.
 * Copyright ISOTOPE Studio
 */
public class Util {
    private static Material getMaterialFromString(String s) {
        return s.matches("[0-9]+") ?
                Material.getMaterial(Integer.parseInt(s)) :
                Material.getMaterial(s);
    }

    @Contract("null -> null")
    public static ItemStack getItemFromConfig(ConfigurationSection s) {
        if (s == null)
            return null;
        String itemName = s.getString("name");
        List<String> lore = s.getStringList("lore");
        Material material = getMaterialFromString(s.getString("type", "NULL"));
        if (material == null) {
            plugin.getLogger().warning("≈‰÷√¥ÌŒÛ: ≤ƒ¡œ" + s.getString("type") + "Œﬁ–ß");
            return null;
        }
        short damage = (short) s.getInt("damage", 0);
        ItemStack item = new ItemStack(material, 1, damage);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(itemName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @NotNull
    public static String getToday() {
        java.util.Date todayDate = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(todayDate);
    }
}
