package cc.isotopestudio.Punctual.gui;

import cc.isotopestudio.Punctual.data.ConfigData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;

import static cc.isotopestudio.Punctual.data.ConfigData.cards;

/**
 * Created by Mars on 7/26/2016.
 * Copyright ISOTOPE Studio
 */
public class CardsGUI extends GUI {

    public CardsGUI(Player player) {
        super(ConfigData.infoGUIName, 18);
        for (int i = 0; i < ConfigData.cardsGUIItems.size(); i++) {
            setOption(i, ConfigData.cardsGUIItems.get(i));
        }
    }

    @Override
    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getName().equals(name)) {
            event.setCancelled(true);
            int slot = event.getRawSlot();
            if (slot < 1 || slot >= size) {
                return;
            }
            if (optionIcons[slot] == null) {
                return;
            }
            if (slot == 8) {
                event.getWhoClicked().closeInventory();
            }
        }
    }
}

