package me.nullchips.hub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.nullchips.hub.utils.ChatUtils;

public class PlayerQuit implements Listener {
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		e.setQuitMessage("");
		ChatUtils.broadcast(e.getPlayer().getName() + " has left the sever.");
	}

}
