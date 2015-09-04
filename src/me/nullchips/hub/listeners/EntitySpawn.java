package me.nullchips.hub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import me.nullchips.hub.utils.SettingsManager;

public class EntitySpawn implements Listener {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	@EventHandler
	public void onEntitySpawn(CreatureSpawnEvent e) {
		
		if (settings.getConfig().getConfigurationSection("spawn") == null) {
			
		}
		else{
			World entityWorld = e.getLocation().getWorld();
			World spawnWorld = Bukkit.getServer().getWorld(settings.getConfig().getString("spawn.world"));
			if(entityWorld.getName().equals(spawnWorld.getName())) {
				e.setCancelled(true);
			}
		}
		
	}

}
