package cc.isotopestudio.Punctual;

import cc.isotopestudio.Punctual.util.PluginFile;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Punctual extends JavaPlugin {

    private static final String pluginName = "Punctual";
    public static final String prefix = (new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD).append("[")
            .append("�¿�").append("]").append(ChatColor.RED).toString();

    public static PluginFile config;
    public static PluginFile playerData;

    @Override
    public void onEnable() {
        config = new PluginFile(this, "config.yml", "config.yml");
        playerData = new PluginFile(this, "playerData.yml");

        getLogger().info(pluginName + "�ɹ�����!");
        getLogger().info(pluginName + "��ISOTOPE Studio����!");
        getLogger().info("http://isotopestudio.cc");
    }

    @Override
    public void onDisable() {
        getLogger().info(pluginName + "�ɹ�ж��!");
    }

}
