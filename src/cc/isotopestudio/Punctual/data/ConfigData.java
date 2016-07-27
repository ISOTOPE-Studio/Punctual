package cc.isotopestudio.Punctual.data;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static cc.isotopestudio.Punctual.Punctual.plugin;

/**
 * Created by Mars on 7/22/2016.
 * Copyright ISOTOPE Studio
 */
public class ConfigData {
    private static final String error = "°Ïc≈‰÷√Œƒº˛¥ÌŒÛ";

    public static ItemStack[] infoGUIItems;
    public static List<Card> cards;
    public static List<ItemStack> cardsGUIItems;
    public static String infoGUIName;
    public static String cardsGUIName;
    public static ItemStack cardsGUIBackButton;

    public static void updateConfig() {

        // Setup infoGUI Info
        infoGUIName = plugin.getConfig().getString("infoGUI.guiName", error);
        infoGUIItems = new ItemStack[4];
        for (int i = 0; i < 4; i++) {
            infoGUIItems[i] = getItemFromConfig(plugin.getConfig().getConfigurationSection("infoGUI." + i));
            System.out.println(infoGUIItems[i]);
        }

        // Setup cardsGUI Info
        cardsGUIBackButton = getItemFromConfig(plugin.getConfig().getConfigurationSection("vipGUI.0"));
        cardsGUIName = plugin.getConfig().getString("vipGUI.guiName", error);

        // Setup Cards and cardsGUI Items
        ConfigurationSection vipsSection = plugin.getConfig().getConfigurationSection("vip");
        Set<String> vipNames = vipsSection.getKeys(false);
        cards = new ArrayList<>();
        cardsGUIItems = new ArrayList<>();
        System.out.println("Cards: " + vipNames);
        for (String vip : vipNames) {
            ConfigurationSection vipSection = vipsSection.getConfigurationSection(vip);
            String name = vipSection.getString("item.name");
            int price = vipSection.getInt("price");
            List<String> rewards = vipSection.getStringList("reward");
            cards.add(new Card(vip, name, price, rewards));
            cardsGUIItems.add(getItemFromConfig(vipSection.getConfigurationSection("item")));

        }
    }

    @SuppressWarnings("deprecation")
    private static Material getMaterialFromString(String s) {
        return s.matches("[0-9]+") ?
                Material.getMaterial(Integer.parseInt(s)) :
                Material.getMaterial(s);
    }

    @Contract("null -> null")
    private static ItemStack getItemFromConfig(ConfigurationSection s) {
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
}
