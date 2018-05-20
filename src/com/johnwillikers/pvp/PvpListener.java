package com.johnwillikers.pvp;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PvpListener implements Listener{
	
	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player damager = (Player) e.getDamager();
			if(e.getEntity() instanceof Player) {
				Player damagee = (Player) e.getEntity();
				//Can they damage the player
				if(!PvpToggle.pvpToggle.get(damager)) {
					e.setCancelled(true);
					damager.sendMessage(ChatColor.RED + "You can't hurt " + damagee.getName() + " because they have PvP disabled!");
				}
				//Can the player be damaged
				if(!PvpToggle.pvpToggle.get(damagee)) {
					e.setCancelled(true);
					damagee.sendMessage(ChatColor.RED + damager.getName() + " tried to hurt you.");
				}
			}
		}
		
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		PvpToggle.pvpToggle.put(e.getPlayer(), false);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		PvpToggle.pvpToggle.remove(e.getPlayer());
	}
}
