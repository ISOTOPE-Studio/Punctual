package cc.isotopestudio.Punctual.data;

import cc.isotopestudio.Punctual.util.Util;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static cc.isotopestudio.Punctual.Punctual.config;

/**
 * Created by Mars on 7/22/2016.
 * Copyright ISOTOPE Studio
 */
public class ConfigData {
    private static final String error = "°Ïc≈‰÷√Œƒº˛¥ÌŒÛ";

    public static ItemStack[] infoGUIItems;
    public static List<Cards> cards;
    public static List<ItemStack> cardsGUIItems;
    public static String infoGUIName;

    public static void updateConfig() {
        infoGUIName = config.getString("infoGUI.guiName", error);

        // Setup infoGUI Items
        infoGUIItems = new ItemStack[4];
        for (int i = 0; i < 4; i++) {
            infoGUIItems[i] = Util.getItemFromConfig(config.getConfigurationSection("infoGUI." + i));
            System.out.println(infoGUIItems[i]);
        }

        // Setup Cards and cardsGUI Items
        ConfigurationSection vipsSection = config.getConfigurationSection("vip");
        Set<String> vipNames = vipsSection.getKeys(false);
        cards = new ArrayList<>();
        cardsGUIItems = new ArrayList<>();
        for (String vip : vipNames) {
            ConfigurationSection vipSection = vipsSection.getConfigurationSection(vip);
            double price = vipSection.getDouble("price");
            List<String> rewards = vipSection.getStringList("reward");
            cards.add(new Cards(vip, price, rewards));
            cardsGUIItems.add(Util.getItemFromConfig(vipSection.getConfigurationSection("item")));
            System.out.println(cardsGUIItems);
        }
    }

}
