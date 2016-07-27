package cc.isotopestudio.Punctual.data;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;

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

    @Contract("_, null -> false")
    public static boolean setPlayerCard(Player player, Card card) {
        if (card == null) return false;
        playerData.set(player.getName() + ".card", card.getId());
        playerData.set(player.getName() + ".time", new Date().getTime());
        return true;
    }

    public static void removePlayerCard(String player) {
        playerData.set(player, null);
    }

    public static void setPlayerChecked(Player player) {
        playerData.set(player.getName() + ".checked", true);
    }

    public static boolean isPlayerChecked(Player player) {
        return playerData.getBoolean(player.getName() + ".checked", false);
    }

}
