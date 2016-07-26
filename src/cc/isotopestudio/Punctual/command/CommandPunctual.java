package cc.isotopestudio.Punctual.command;

import cc.isotopestudio.Punctual.gui.InfoGUI;
import cc.isotopestudio.Punctual.util.S;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mars on 7/22/2016.
 * Copyright ISOTOPE Studio
 */
public class CommandPunctual implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("Punctual")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(S.toPrefixRed("Íæ¼ÒÖ´ÐÐµÄÃüÁî"));
                return true;
            }
            Player player = (Player) sender;
            new InfoGUI(player).open(player);
            return true;
        }
        return false;
    }
}