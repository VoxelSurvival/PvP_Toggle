package com.johnwillikers.pvp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PvpCommands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("pvp")) {
			boolean pvp = PvpToggle.pvpToggle.get(player);
			PvpToggle.log("PVP", "DERP", "Pvp Toggle boolean = " + pvp);
			if(pvp) {
				PvpToggle.pvpToggle.replace(player, false);
				player.sendMessage(ChatColor.GREEN + "You are no longer flagged for pvp!");
				return true;
			}
			PvpToggle.pvpToggle.replace(player, true);
			player.sendMessage(ChatColor.RED + "You are flagged for pvp!");
			return true;
		}
		return false;
	}

}
