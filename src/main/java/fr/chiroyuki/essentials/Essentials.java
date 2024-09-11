package fr.chiroyuki.essentials;

import fr.chiroyuki.essentials.Cmd.*;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Essentials extends JavaPlugin implements Listener {
    private HomeManager homeManager;

    @Override
    public void onEnable() {
        // Enregistrer le listener
        getServer().getPluginManager().registerEvents(this, this);

        // Command setup
        this.getCommand("gm").setExecutor(new GamemodeCommand());
        this.getCommand("fly").setExecutor(new FlyCommand());

        homeManager = new HomeManager(this);
        getCommand("sethome").setExecutor(new HomeCommand(homeManager));
        getCommand("home").setExecutor(new HomeCommand(homeManager));
        getCommand("delhome").setExecutor(new HomeCommand(homeManager));
        getCommand("homes").setExecutor(new HomeCommand(homeManager));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        homeManager.saveConfig();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Material blockType = event.getBlock().getType();
        event.getPlayer().sendMessage("You broke " + blockType);
    }
}
