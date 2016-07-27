package cc.isotopestudio.Punctual.task;

import cc.isotopestudio.Punctual.data.PlayerData;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

import static cc.isotopestudio.Punctual.Punctual.plugin;

/**
 * Created by Mars on 7/22/2016.
 * Copyright ISOTOPE Studio
 */
public class DateUpdate extends BukkitRunnable {

    private static final String path = "today.date";

    @Override
    public void run() {
        String configDate = plugin.getPlayersData().getString(path);
        if (configDate == null || !configDate.equals(getToday())) {
            plugin.getPlayersData().set(path, getToday());
            for (String player : plugin.getPlayersData().getKeys(false)) {
                plugin.getPlayersData().set(player + ".checked", null);
            }
        }
        for (String player : plugin.getPlayersData().getKeys(false)) {
            if (PlayerData.getRemainDays(player) <= 0)
                PlayerData.removePlayerCard(player);
        }
        plugin.savePlayersData();
    }

    @NotNull
    private static String getToday() {
        java.util.Date todayDate = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(todayDate);
    }
}
