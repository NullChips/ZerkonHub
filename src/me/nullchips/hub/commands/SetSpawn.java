package me.nullchips.hub.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nullchips.hub.utils.ChatUtils;
import me.nullchips.hub.utils.SettingsManager;

public class SetSpawn implements CommandExecutor {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("setspawn")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "The console cannot use this command.");
				return false;
			}
			
			Player p = (Player) sender;
			
			if(!p.hasPermission("hub.setspawn")) {
				ChatUtils.noPermission(p);
				return false;
			}
			
			settings.getConfig().set("spawn.world", p.getLocation().getWorld().getName());
			settings.getConfig().set("spawn.x", p.getLocation().getX());
			settings.getConfig().set("spawn.y", p.getLocation().getY());
			settings.getConfig().set("spawn.z", p.getLocation().getZ());
			p.sendMessage(ChatColor.DARK_AQUA + "The spawn has been set.");
			settings.saveConfig();
		}
		
		return true;
	}

}
