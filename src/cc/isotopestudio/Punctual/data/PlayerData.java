package cc.isotopestudio.Punctual.data;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;

import java.util.Date;

import static cc.isotopestudio.Punctual.Punctual.plugin;

/**
 * Created by Mars on 7/22/2016.
 * Copyright ISOTOPE Studio
 */
public class PlayerData {

    /*
    Sample of player data
    today:
      date: 1000
    Mars:
      card: a
      time: <long>
      checked: true
     */

    public static Card getPlayerCard(Player player) {
        return Card.getCardByName(plugin.getPlayersData().getString(player.getName() + ".card"));
    }

    @Contract("_, null -> false")
    public static boolean setPlayerCard(Player player, Card card) {
        if (card == null) return false;
        plugin.getPlayersData().set(player.getName() + ".card", card.getId());
        plugin.getPlayersData().set(player.getName() + ".time", new Date().getTime());
        plugin.savePlayersData();
        return true;
    }

    public static void removePlayerCard(String player) {
        plugin.getPlayersData().set(player, null);
        plugin.savePlayersData();
    }

    public static void setPlayerChecked(Player player) {
        plugin.getPlayersData().set(player.getName() + ".checked", true);
        plugin.savePlayersData();
    }

    public static boolean isPlayerChecked(Player player) {
        return plugin.getPlayersData().getBoolean(player.getName() + ".checked", false);
    }

}
