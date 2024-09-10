package fr.chiroyuki.essentials.Cmd;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /gm <gamemode>");
            return true;
        }

        String mode = args[0].toLowerCase();

        switch (mode) {
            case "0":
            case "survival":
            case "s":
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage("Game Mode changed to Survival.");
                break;
            case "1":
            case "creative":
            case "c":
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage("Game Mode changed to Creative.");
                break;
            case "2":
            case "adventure":
            case "a":
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage("Game Mode changed to Adventure.");
                break;
            case "3":
            case "spectator":
            case "sp":
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage("Game Mode changed to Spectator.");
                break;
            default:
                player.sendMessage("Unknown Game Mode. Available Game Modes: survival | creative | adventure | spectator | s | c | a | sp");
                break;
        }

        return true;
    }
}
