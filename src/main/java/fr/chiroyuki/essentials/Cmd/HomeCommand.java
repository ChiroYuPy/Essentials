package fr.chiroyuki.essentials.Cmd;

import fr.chiroyuki.essentials.HomeManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import java.util.Set;

public class HomeCommand implements CommandExecutor {
    private final HomeManager homeManager;

    public HomeCommand(HomeManager homeManager) {
        this.homeManager = homeManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return false;
        }

        switch (label.toLowerCase()) { //test de commentaire
            case "sethome":
                if (args.length != 1) {
                    return false;
                }
                String setName = args[0];
                homeManager.setHome(player, setName, player.getLocation());
                player.sendMessage("Home '" + setName + "' set!");
                break;

            case "home":
                if (args.length != 1) {
                    return false;
                }
                String homeName = args[0];
                Location home = homeManager.getHome(player, homeName);
                if (home != null) {
                    player.teleport(home);
                    player.sendMessage("Teleported to home '" + homeName + "'!");
                } else {
                    player.sendMessage("No home found with that name!");
                }
                break;

            case "delhome":
                if (args.length != 1) {
                    return false;
                }
                String deleteName = args[0];
                homeManager.deleteHome(player, deleteName);
                player.sendMessage("Home '" + deleteName + "' deleted!");
                break;

            case "homes":
                Set<String> homes = homeManager.listHomes(player);
                if (homes.isEmpty()) {
                    player.sendMessage("You have no homes set.");
                } else {
                    StringBuilder sb = new StringBuilder("Your homes: ");
                    for (String homeName1 : homes) {
                        sb.append(homeName1).append(", ");
                    }
                    player.sendMessage(sb.toString());
                }
                break;

            default:
                return false;
        }

        return true;
    }
}
