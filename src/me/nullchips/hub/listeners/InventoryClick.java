package me.nullchips.hub.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.nullchips.hub.utils.SettingsManager;

public class InventoryClick implements Listener {
	
	SettingsManager settings = SettingsManager.getInstance();
	
	@EventHandler
	public void onInventoyClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(p.getLocation().getWorld().getName().equals(settings.getConfig().getString("spawn.world"))) {
			if(!p.hasPermission("hub.editinventory")) {
				e.setCancelled(true);
			}
			
			//TODO Create compass selector inventory.
			
			e.setCancelled(true);
		}
	}

}
