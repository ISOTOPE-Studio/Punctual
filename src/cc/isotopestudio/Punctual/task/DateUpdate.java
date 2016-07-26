package cc.isotopestudio.Punctual.task;

import cc.isotopestudio.Punctual.data.PlayerData;
import cc.isotopestudio.Punctual.util.Util;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Date;

import static cc.isotopestudio.Punctual.Punctual.playerData;

/**
 * Created by Mars on 7/22/2016.
 * Copyright ISOTOPE Studio
 */
public class DateUpdate extends BukkitRunnable {

    private static final String path = "today.date";

    @Override
    public void run() {
        String configDate = playerData.getString(path);
        if (configDate == null || !configDate.equals(Util.getToday())) {
            playerData.set(path, Util.getToday());
            for (String player : playerData.getKeys(false)) {
                playerData.set(player + ".checked", null);
            }
        }
        for (String player : playerData.getKeys(false)) {
            Date date = new Date(playerData.getLong(player + ".time"));
            long time = playerData.getLong(player + ".time");
            if (time == 0) continue;
            long expire = time + 2592000000L;
            if (new Date().getTime() > expire) {
                PlayerData.removePlayerCard(player);
            }
        }
    }
}
