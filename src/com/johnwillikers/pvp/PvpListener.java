package com.johnwillikers.pvp;

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
					damager.sendMessage("Yo doggie you can't hurt them");
				}
				//Can the player be damaged
				if(!PvpToggle.pvpToggle.get(damagee)) {
					e.setCancelled(true);
					damager.sendMessage(damagee.getDisplayName() + " has their PvP toggled off");
				}
			}
		}
		
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		PvpToggle.pvpToggle.put(e.getPlayer(), true);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		PvpToggle.pvpToggle.remove(e.getPlayer());
	}
}
