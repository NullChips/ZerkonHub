package me.nullchips.hub.listeners;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.FireworkMeta;

import me.nullchips.hub.Hub;
import me.nullchips.hub.utils.ChatUtils;
import me.nullchips.hub.utils.SettingsManager;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class PlayerLogin implements Listener {

	SettingsManager settings = SettingsManager.getInstance();

	@EventHandler
	public void onPlayerLogin(final PlayerLoginEvent e) {

		final Player p = e.getPlayer();
		final PlayerInventory pi = e.getPlayer().getInventory();

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Hub.getPlugin(), new Runnable() {
			public void run() {

				Firework f = (Firework) e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), Firework.class);

				FireworkMeta fm = f.getFireworkMeta();
				fm.addEffect(FireworkEffect.builder()
						.flicker(false)
						.trail(true)
						.with(Type.CREEPER)
						.withColor(Color.SILVER)
						.withFade(Color.ORANGE)
						.build());
				fm.setPower(3);
				f.setFireworkMeta(fm);

				World w = Bukkit.getServer().getWorld(settings.getConfig().getString("spawn.world"));
				double x = settings.getConfig().getDouble("spawn.x");
				double y = settings.getConfig().getDouble("spawn.y");
				double z = settings.getConfig().getDouble("spawn.z");
				
				p.teleport(new Location(w, x, y ,z));
				
				ItemStack compass = new ItemStack(Material.COMPASS);
				pi.setItem(4, compass);
				
				PacketPlayOutTitle times = new PacketPlayOutTitle(30, 100, 15); //The time the title is displayed in ticks
				PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("\"§2Welcome to ServerName!\""));
				PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("\"§3Subtitle text goes here!\""));
				for(@SuppressWarnings("rawtypes") Packet packet : new Packet[] {times, title, subtitle}) {
				    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
				}
				PacketPlayOutPlayerListHeaderFooter headerfooter = new PacketPlayOutPlayerListHeaderFooter();
				try {
				    Field header = headerfooter.getClass().getDeclaredField("a");
				    Field footer = headerfooter.getClass().getDeclaredField("b");
				    header.setAccessible(true);
				    footer.setAccessible(true);
				    header.set(headerfooter, ChatSerializer.a("\"§2Welcome to ServerName!\""));
				    footer.set(headerfooter, ChatSerializer.a("\"§3Subtitle text goes here!\""));
				} catch (Exception ex) {
				    ex.printStackTrace();
				}
				((CraftPlayer) p).getHandle().playerConnection.sendPacket(headerfooter);

				ChatUtils.message(p, "Welcome to ServerName!");

			}
		}, 2);
	}
}
