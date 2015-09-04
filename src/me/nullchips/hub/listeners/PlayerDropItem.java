package me.nullchips.hub.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.nullchips.hub.utils.SettingsManager;

public class PlayerDropItem implements Listener {

	SettingsManager settings = SettingsManager.getInstance();

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if(p.getLocation().getWorld().getName().equals(settings.getConfig().getString("spawn.world"))) {
			if(!p.hasPermission("hub.editinventory")) {
				e.setCancelled(true);
			}
		}
	}

}
