package cc.isotopestudio.Punctual.task;

import cc.isotopestudio.Punctual.data.ConfigData;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Mars on 7/22/2016.
 * Copyright ISOTOPE Studio
 */
public class InfoUpdate extends BukkitRunnable{

    @Override
    public void run() {
        ConfigData.updateConfig();

    }
}
