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
    public static List<Card> cards;
    public static List<ItemStack> cardsGUIItems;
    public static String infoGUIName;
    public static String cardsGUIName;
    public static ItemStack cardsGUIBackButton;

    public static void updateConfig() {

        // Setup infoGUI Info
        infoGUIName = config.getString("infoGUI.guiName", error);
        infoGUIItems = new ItemStack[4];
        for (int i = 0; i < 4; i++) {
            infoGUIItems[i] = Util.getItemFromConfig(config.getConfigurationSection("infoGUI." + i));
            System.out.println(infoGUIItems[i]);
        }

        // Setup cardsGUI Info
        cardsGUIBackButton = Util.getItemFromConfig(config.getConfigurationSection("vipGUI.0"));
        cardsGUIName = config.getString("vipGUI.guiName", error);

        // Setup Cards and cardsGUI Items
        ConfigurationSection vipsSection = config.getConfigurationSection("vip");
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
            cardsGUIItems.add(Util.getItemFromConfig(vipSection.getConfigurationSection("item")));

        }
    }

}
