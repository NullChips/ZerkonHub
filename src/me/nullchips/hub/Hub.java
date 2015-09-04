package me.nullchips.hub;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.nullchips.hub.commands.HubReload;
import me.nullchips.hub.commands.SetSpawn;
import me.nullchips.hub.commands.Spawn;
import me.nullchips.hub.commands.ToggleHubProtection;
import me.nullchips.hub.listeners.BlockBreak;
import me.nullchips.hub.listeners.BlockPlace;
import me.nullchips.hub.listeners.EntityDamage;
import me.nullchips.hub.listeners.EntitySpawn;
import me.nullchips.hub.listeners.FoodLevelChange;
import me.nullchips.hub.listeners.PlayerDropItem;
import me.nullchips.hub.listeners.PlayerJoin;
import me.nullchips.hub.listeners.PlayerLogin;
import me.nullchips.hub.listeners.PlayerQuit;
import me.nullchips.hub.utils.ChatUtils;
import me.nullchips.hub.utils.ProtectionState;
import me.nullchips.hub.utils.SettingsManager;

public class Hub extends JavaPlugin {
	
	//TODO Change name of plugin in plugin.yml file.

	private static Plugin pl;
	
	SettingsManager settings = SettingsManager.getInstance();

	public void onEnable() {

		pl = this;
		
		settings.setup(pl);
		
		ProtectionState.setState(ProtectionState.ENABLED);
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				if(ProtectionState.isState(ProtectionState.DISABLED)) {
					int count = 0;
					for(Player p : Bukkit.getServer().getOnlinePlayers()) {
						if(p.hasPermission("hub.toggleprotecion")) {
							ChatUtils.message(p, ChatColor.RED + "The hub protection is disabled!");
							count++;
						}
					}
					if(count == 0) {
						ProtectionState.setState(ProtectionState.DISABLED);
					}
				}
			}
		}, 0, 600);
		
		//LISTENERS
	    registerEvents(this, new PlayerLogin());
	    registerEvents(this, new PlayerJoin());
	    registerEvents(this, new PlayerQuit());
	    registerEvents(this, new EntityDamage());
	    registerEvents(this, new EntitySpawn());
	    registerEvents(this, new BlockBreak());
	    registerEvents(this, new BlockPlace());
	    registerEvents(this, new FoodLevelChange());
	    registerEvents(this, new PlayerDropItem());
		
		//COMMANDS
		getCommand("setspawn").setExecutor(new SetSpawn());
		getCommand("spawn").setExecutor(new Spawn());
		getCommand("hubreload").setExecutor(new HubReload());
		getCommand("togglehubprotection").setExecutor(new ToggleHubProtection());

	}

	public void onDisable() {
		pl = null;
	}

	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}
	
	public static Plugin getPlugin() {
		return pl;
	}
}

//TODO Stop players from rearranging inventory or dropping itemsin the hub.
