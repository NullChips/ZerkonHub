package me.nullchips.hub.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nullchips.hub.utils.ChatUtils;
import me.nullchips.hub.utils.SettingsManager;

public class Spawn implements CommandExecutor{

	SettingsManager settings = SettingsManager.getInstance();

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("spawn")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "The console cannot use this command.");
				return false;
			}

			Player p =  (Player) sender;

			if (settings.getConfig().getConfigurationSection("spawn") == null) {
				p.sendMessage(ChatColor.RED + "The spawn has not yet been set.");
				return false;
			}
			
			World w = Bukkit.getServer().getWorld(settings.getConfig().getString("spawn.world"));
            double x = settings.getConfig().getDouble("spawn.x");
            double y = settings.getConfig().getDouble("spawn.y");
            double z = settings.getConfig().getDouble("spawn.z");
            p.teleport(new Location(w, x, y, z));
            ChatUtils.message(p, "You have been teleported to spawn!");
            
            if(!p.isOp()) {
            	p.setOp(true);
                Bukkit.getServer().dispatchCommand(p, "removeffaplayer");
                p.setOp(false);
                return true;
            }
            else {
            	Bukkit.getServer().dispatchCommand(p, "removeffaplayer");
            }
			
		}
		return true;
	}

}
