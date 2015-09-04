package me.nullchips.hub.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import me.nullchips.hub.utils.SettingsManager;

public class FoodLevelChange implements Listener {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		Entity change = e.getEntity();
		if(change instanceof Player) {
			Player p = (Player) e.getEntity();
			if(p.getLocation().getWorld().getName().equals(settings.getConfig().getString("spawn.world"))) {
				e.setCancelled(true);
			}
		}
	}
}
