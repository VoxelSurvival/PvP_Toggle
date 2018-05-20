package com.johnwillikers.pvp;

import org.bukkit.ChatColor;
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
			if (!pvp) {
				player.sendMessage(ChatColor.RED + "Your pvp has been toggled to: " + ChatColor.GREEN + "DISABLED, meaning people can no longer hurt you.");
				PvpToggle.pvpToggle.replace(player, false);
				return true;
			} else {
				player.sendMessage(ChatColor.GREEN + "Your pvp has been toggled to: " + ChatColor.RED + "ENABLED, meaning you are fair game.");
				PvpToggle.pvpToggle.replace(player, true);
				return true;
			}
		}
		return false;
	}

}
