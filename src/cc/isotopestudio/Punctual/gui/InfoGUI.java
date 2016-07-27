package cc.isotopestudio.Punctual.gui;

import cc.isotopestudio.Punctual.data.Card;
import cc.isotopestudio.Punctual.data.ConfigData;
import cc.isotopestudio.Punctual.data.PlayerData;
import cc.isotopestudio.Punctual.util.S;
import org.bukkit.Bukkit;
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
            // �����¿�
            if (slot == 1) {
                player.closeInventory();
                new CardsGUI(player).open(player);
                return;
            }
            // ǩ��
            if (slot == 2) {
                Card card = PlayerData.getPlayerCard(player);
                if (card == null) {
                    player.sendMessage(S.toPrefixRed("��û���¿�"));
                    player.closeInventory();
                    return;
                }
                if (PlayerData.isPlayerChecked(player)) {
                    player.sendMessage(S.toPrefixRed("���Ѿ�ǩ������"));
                    player.closeInventory();
                    return;
                }
                PlayerData.setPlayerChecked(player);
                for (String command : card.getCommands()) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                            command.replace("<player>", player.getName()));
                }
                player.closeInventory();
                player.sendMessage(S.toPrefixGreen("�ɹ�ǩ��, ��ý���"));
            }
            if (slot == 8) {
                player.closeInventory();
            }
        }
    }
}

