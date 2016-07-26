package cc.isotopestudio.Punctual.gui;

import cc.isotopestudio.Punctual.data.ConfigData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by Mars on 7/26/2016.
 * Copyright ISOTOPE Studio
 */
public class InfoGUI extends GUI {

    public InfoGUI(Player player) {
        super(ConfigData.infoGUIName, 9);
        setOption(0, ConfigData.infoGUIItems[0]);
        setOption(1, ConfigData.infoGUIItems[1]);
        setOption(2, ConfigData.infoGUIItems[2]);
        setOption(8, ConfigData.infoGUIItems[3]);
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
            Player player = (Player) event.getWhoClicked();
            // ¹ºÂòÔÂ¿¨
            if (slot == 1) {
                event.getWhoClicked().closeInventory();
                new CardsGUI(player).open(player);
                return;
            }
            if (slot == 2) {
                return;
            }
            if (slot == 8) {
                event.getWhoClicked().closeInventory();
                return;
            }
        }
    }
}

