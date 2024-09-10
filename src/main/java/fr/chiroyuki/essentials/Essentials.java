package fr.chiroyuki.essentials;

import fr.chiroyuki.essentials.Cmd.FlyCommand;
import fr.chiroyuki.essentials.Cmd.GamemodeCommand;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Essentials extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Enregistrer le listener
        getServer().getPluginManager().registerEvents(this, this);

        // Command setup
        this.getCommand("gm").setExecutor(new GamemodeCommand()); // /gm
        this.getCommand("fly").setExecutor(new FlyCommand()); // /fly
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Material blockType = event.getBlock().getType();
        event.getPlayer().sendMessage("You broke " + blockType);
    }
}
