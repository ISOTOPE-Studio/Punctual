package cc.isotopestudio.Punctual.gui;

import cc.isotopestudio.Punctual.data.Card;
import cc.isotopestudio.Punctual.data.ConfigData;
import cc.isotopestudio.Punctual.data.PlayerData;
import cc.isotopestudio.Punctual.util.S;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static cc.isotopestudio.Punctual.Punctual.credit;
import static cc.isotopestudio.Punctual.data.ConfigData.cards;

/**
 * Created by Mars on 7/26/2016.
 * Copyright ISOTOPE Studio
 */
class CardsGUI extends GUI {

    CardsGUI(Player player) {
        super(ConfigData.cardsGUIName, 18);
        for (int i = 0; i < ConfigData.cardsGUIItems.size(); i++) {
            setOption(i, ConfigData.cardsGUIItems.get(i));
        }

        Card card = PlayerData.getPlayerCard(player);
        ItemStack infoItem = new ItemStack(Material.EMERALD);
        List<String> lore = new ArrayList<>();
        if (card != null) {
            lore.add(S.toAqua("当前月卡: ") + card.getName());
            lore.add(S.toAqua("剩余天数: ") + S.toGreen(PlayerData.getRemainDays(player.getName()) + "天"));
        } else {
            lore.add(S.toRed("你没有月卡"));
        }
        ItemMeta meta = infoItem.getItemMeta();
        meta.setDisplayName(player.getDisplayName() + " §a的月卡");
        meta.setLore(lore);
        infoItem.setItemMeta(meta);

        setOption(16, infoItem);

        setOption(17, ConfigData.cardsGUIBackButton);
    }

    @Override
    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getName().equals(name)) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            int slot = event.getRawSlot();
            if (slot == 17) {
                event.getWhoClicked().closeInventory();
                new InfoGUI(player).open(player);
                return;
            }
            if (slot < 0 || slot >= 16) {
                return;
            }
            if (optionIcons[slot] == null) {
                return;
            }
            Card card = cards.get(slot);
            if (!player.hasPermission(card.getPermission())) {
                player.sendMessage(S.toPrefixRed("你没有权限购买"));
                player.closeInventory();
                return;
            }
            if (PlayerData.getPlayerCard(player) != null) {
                player.sendMessage(S.toPrefixRed("你现在已经有月卡了"));
                player.closeInventory();
                return;
            }
            if (!credit.takeCredit(player.getName(), card.getPrice())) {
                player.sendMessage(S.toPrefixRed("点卷不足"));
                player.closeInventory();
                return;
            }
            PlayerData.setPlayerCard(player, card);
            player.sendMessage(S.toPrefixGreen("成功购买, 花费 " + card.getPrice() + " 点卷"));
            player.closeInventory();
        }
    }
}

