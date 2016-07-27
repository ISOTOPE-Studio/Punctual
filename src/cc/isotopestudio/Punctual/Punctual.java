package cc.isotopestudio.Punctual;

import cc.isotopestudio.Punctual.command.CommandPunctual;
import cc.isotopestudio.Punctual.task.DateUpdate;
import cc.isotopestudio.Punctual.task.InfoUpdate;
import io.github.sjj118.credit.CreditAPI;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Punctual extends JavaPlugin {

    private static final String pluginName = "Punctual";
    public static final String prefix = (new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD).append("[")
            .append("月卡").append("]").append(ChatColor.RED).toString();

    public static Punctual plugin;
    public static CreditAPI credit;

    @Override
    public void onEnable() {
        plugin = this;

        if (getServer().getPluginManager().getPlugin("Credit") == null) {
            getLogger().warning("Credit 不存在!");
            getServer().getPluginManager().disablePlugin(this);
        }
        credit = new CreditAPI();

        saveDefaultConfig();
        try {
            getPlayersData().save(dataFile);
        } catch (IOException e) {
            getLogger().info("玩家文件出错！");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }


        this.getCommand("Punctual").setExecutor(new CommandPunctual());

        new InfoUpdate().runTaskLater(this, 1);
        new DateUpdate().runTaskTimer(this, 1, 20 * 60 * 60);

        getLogger().info(pluginName + "成功加载!");
        getLogger().info(pluginName + "由ISOTOPE Studio制作!");
        getLogger().info("http://isotopestudio.cc");
    }

    @Override
    public void onDisable() {
        getLogger().info(pluginName + "成功卸载!");
    }

    private File dataFile = null;
    private FileConfiguration data = null;

    private void reloadPlayersData() {
        if (dataFile == null) {
            dataFile = new File(getDataFolder(), "playerData.yml");
        }
        data = YamlConfiguration.loadConfiguration(dataFile);
    }

    public FileConfiguration getPlayersData() {
        if (data == null) {
            reloadPlayersData();
        }
        return data;
    }

    public void savePlayersData() {
        if (data == null || dataFile == null) {
            return;
        }
        try {
            getPlayersData().save(dataFile);
        } catch (IOException ex) {
            getLogger().info("玩家文件保存失败！");
        }
    }

}
