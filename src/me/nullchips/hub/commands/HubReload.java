package me.nullchips.hub.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nullchips.hub.utils.ChatUtils;
import me.nullchips.hub.utils.SettingsManager;

public class HubReload implements CommandExecutor {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("hubreload")) {
			Player p = (Player) sender;
			if(!sender.hasPermission("hub.reload")) {
				ChatUtils.noPermission(p);
				return true;
			}
			settings.reloadConfig();
			p.sendMessage(ChatColor.DARK_AQUA + "The hubs config has been reloaded.");
		}		
		return true;
	}

}
