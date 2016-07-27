package cc.isotopestudio.Punctual;

import cc.isotopestudio.Punctual.command.CommandPunctual;
import cc.isotopestudio.Punctual.task.DateUpdate;
import cc.isotopestudio.Punctual.task.InfoUpdate;
import cc.isotopestudio.Punctual.util.PluginFile;
import io.github.sjj118.credit.CreditAPI;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Punctual extends JavaPlugin {

    private static final String pluginName = "Punctual";
    public static final String prefix = (new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD).append("[")
            .append("�¿�").append("]").append(ChatColor.RED).toString();

    public static PluginFile config;
    public static PluginFile playerData;

    public static Punctual plugin;
    public static CreditAPI credit;

    @Override
    public void onEnable() {
        plugin = this;

        if (getServer().getPluginManager().getPlugin("Credit") == null) {
            getLogger().warning("Credit ������!");
            getServer().getPluginManager().disablePlugin(this);
        }
        credit = new CreditAPI();

        config = new PluginFile(this, "config.yml", "config.yml");
        playerData = new PluginFile(this, "playerData.yml");

        this.getCommand("Punctual").setExecutor(new CommandPunctual());

        new InfoUpdate().runTaskLater(this, 1);
        new DateUpdate().runTaskTimer(this, 1, 20 * 60 * 60);

        getLogger().info(pluginName + "�ɹ�����!");
        getLogger().info(pluginName + "��ISOTOPE Studio����!");
        getLogger().info("http://isotopestudio.cc");
    }

    @Override
    public void onDisable() {
        getLogger().info(pluginName + "�ɹ�ж��!");
    }

}
