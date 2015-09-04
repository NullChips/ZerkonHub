package me.nullchips.hub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.nullchips.hub.utils.SettingsManager;

public class EntityDamage implements Listener {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	@EventHandler
	public void onPlayerDamageEvent(EntityDamageEvent event) {	
		
	    if(event.getEntity() instanceof Player){
	    	
	    	Player p = (Player) event.getEntity();
			
			World spawnWorld = Bukkit.getServer().getWorld(settings.getConfig().getString("spawn.world"));
			World playerWorld = p.getLocation().getWorld();
			
	        if(playerWorld.getName().equals(spawnWorld.getName())) {
	            event.setCancelled(true);
	        }
	    }else{
	        event.setCancelled(false);
	        }
	    }
	 

}
