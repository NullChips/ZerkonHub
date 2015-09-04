package me.nullchips.hub.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nullchips.hub.utils.ChatUtils;
import me.nullchips.hub.utils.ProtectionState;

public class ToggleHubProtection implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("togglehubprotection")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "The console cannot use this command.");
				return false;
			}
			
			Player p = (Player) sender;
			if(!p.hasPermission("hub.toggleprotection")) {
				ChatUtils.noPermission(p);
				return false;
			}
			if(ProtectionState.isState(ProtectionState.ENABLED)) {
				ProtectionState.setState(ProtectionState.DISABLED);
				ChatUtils.message(p, ChatColor.RED + "Hub protection is disabled! This should only be used when the server is not public as this means that players with the hub.build permission can break blocks in the hub or build in the hub! If you didn't mean to do this, type /togglehubprotection again!");
				return true;
			}
			if(ProtectionState.isState(ProtectionState.DISABLED)) {
				ProtectionState.setState(ProtectionState.ENABLED);
				ChatUtils.message(p, "Hub protection is re-enabled.");
			}
		}
		
		return true;
	}

}
