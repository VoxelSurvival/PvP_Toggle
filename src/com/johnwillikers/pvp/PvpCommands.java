package com.johnwillikers.pvp;

import org.bukkit.Bukkit;
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
			if (pvp) {
				player.sendMessage(ChatColor.RED + "Your pvp has been toggled to: " + ChatColor.GREEN + "DISABLED, meaning people can no longer hurt you.");
				PvpToggle.pvpToggle.replace(player, false);
				return true;
			} else {
				player.sendMessage(ChatColor.GREEN + "Your pvp has been toggled to: " + ChatColor.RED + "ENABLED, meaning you are fair game.");
				PvpToggle.pvpToggle.replace(player, true);
				return true;
			}
		} else if (label.equalsIgnoreCase("setpvp")) {
			if(args.length == 0) {
				player.sendMessage(ChatColor.RED + "You have to use /togglepvp <player>!");
			} else if (args.length == 1) {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target == null) {
					player.sendMessage(ChatColor.RED + "Could not find player specified.");
				} else {
					player.sendMessage(ChatColor.GREEN + "Successfully changed pvp status and informed the player of the change.");
					target.sendMessage(ChatColor.GREEN + "Your pvp status has been forcefully changed by " + player.getName());
					if (PvpToggle.pvpToggle.containsKey(target)) {
						boolean cur = PvpToggle.pvpToggle.get(target);
						if (cur) {
							PvpToggle.pvpToggle.replace(target, false);
						} else if (!cur) {
							PvpToggle.pvpToggle.replace(target, true);
						}
					} else {
						PvpToggle.log("SetPvP", "WARN", "An error has occurred while performing command /setpvp by " + player.getName() + " on player " + target.getName());
					}
				}
			}
			
			
		}
		return false;
	}

}
