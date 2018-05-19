package com.johnwillikers.pvp;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class PvpToggle extends JavaPlugin{

	
	public static void log(String name, String type, String msg){
		Server server = Bukkit.getServer();
		ConsoleCommandSender console = server.getConsoleSender();
		console.sendMessage(ChatColor.WHITE + "[" + name + ChatColor.WHITE + "]|" + "[" + type + ChatColor.WHITE + "] " + msg);
	}
	
	//True = Can Damage and be damaged
	public static HashMap<Player, Boolean> pvpToggle = new HashMap<Player, Boolean>();
	
	@Override
	public void onEnable() {
		this.getCommand("pvp").setExecutor(new PvpCommands());
		getServer().getPluginManager().registerEvents(new PvpListener(), this);
		for(Player p : Bukkit.getOnlinePlayers()) {
			pvpToggle.put(p, true);
		}
	}
	
	@Override
	public void onDisable() {
		
	}
}
