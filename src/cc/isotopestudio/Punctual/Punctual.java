package cc.isotopestudio.Punctual;

import cc.isotopestudio.Punctual.util.PluginFile;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Punctual extends JavaPlugin {

    private static final String pluginName = "Punctual";
    public static final String prefix = (new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD).append("[")
            .append("月卡").append("]").append(ChatColor.RED).toString();

    public static PluginFile config;
    public static PluginFile playerData;

    @Override
    public void onEnable() {
        config = new PluginFile(this, "config.yml", "config.yml");
        playerData = new PluginFile(this, "playerData.yml");

        getLogger().info(pluginName + "成功加载!");
        getLogger().info(pluginName + "由ISOTOPE Studio制作!");
        getLogger().info("http://isotopestudio.cc");
    }

    @Override
    public void onDisable() {
        getLogger().info(pluginName + "成功卸载!");
    }

}
