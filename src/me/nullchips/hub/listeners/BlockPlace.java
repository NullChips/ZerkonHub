package me.nullchips.hub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.nullchips.hub.utils.ProtectionState;
import me.nullchips.hub.utils.SettingsManager;

public class BlockPlace implements Listener {

	SettingsManager settings = SettingsManager.getInstance();

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(ProtectionState.isState(ProtectionState.ENABLED)) {
			World blockWorld = e.getBlock().getLocation().getWorld();
			World spawnWorld = Bukkit.getServer().getWorld(settings.getConfig().getString("spawn.world"));
			if(blockWorld.getName().equals(spawnWorld.getName())){
				Player p = e.getPlayer();
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "You cannot place blocks here.");
			}
		}
		if(ProtectionState.isState(ProtectionState.DISABLED)) {
			if(!e.getPlayer().hasPermission("hub.build")) {
				Player p = e.getPlayer();
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "You cannot place blocks here.");
			}
		}
	}

}
