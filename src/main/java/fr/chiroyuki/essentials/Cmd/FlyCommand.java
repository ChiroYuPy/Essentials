package fr.chiroyuki.essentials.Cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        if (args.length > 0) {
            String argument = args[0].toLowerCase();
            if (argument.equals("on")) {
                player.setAllowFlight(true);
                player.sendMessage("Fly §aOn");
                return true;

            } else if (argument.equals("off")) {
                player.setAllowFlight(false);
                player.sendMessage("Fly §cOff");
                return true;

            } else {
                player.sendMessage("Unknown argument: " + argument);
                return true;
            }
        }

        boolean canFly = player.getAllowFlight();
        player.setAllowFlight(!canFly);

        if (player.getAllowFlight()) {
            player.sendMessage("Fly §aOn");
        } else {
            player.sendMessage("Fly §cOff");
        }

        return true;
    }
}
