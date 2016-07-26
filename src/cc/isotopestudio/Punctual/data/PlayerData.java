package cc.isotopestudio.Punctual.data;

import org.bukkit.entity.Player;

import java.util.Date;

import static cc.isotopestudio.Punctual.Punctual.playerData;

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
        return Card.getCardByName(playerData.getString(player.getName() + ".card"));
    }

    public static boolean setPlayerCard(Player player, String cardName) {
        Card card = Card.getCardByName(cardName);
        if (card == null) return false;
        playerData.set(player.getName() + ".card", cardName);
        return true;
    }

    public static void setPlayerTimeStamp(Player player) {
        playerData.set(player.getName() + ".time", new Date().getTime());
    }

    public static void removePlayerCard(String player) {
        playerData.set(player, null);
    }

    public static boolean isPlayerChecked(Player player) {
        return playerData.getBoolean(player.getName() + ".checked", false);
    }

}
