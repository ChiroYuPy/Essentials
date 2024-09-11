package fr.chiroyuki.essentials;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class HomeManager {
    private final File homesFile;
    private final FileConfiguration homesConfig;

    public HomeManager(JavaPlugin plugin) {
        // Initialize file and config
        homesFile = new File(plugin.getDataFolder(), "homes.yml");
        if (!homesFile.exists()) {
            try {
                homesFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        homesConfig = YamlConfiguration.loadConfiguration(homesFile);
    }

    public void setHome(Player player, String name, Location location) {
        String path = "homes." + player.getUniqueId().toString() + "." + name;
        homesConfig.set(path + ".world", location.getWorld().getName());
        homesConfig.set(path + ".x", location.getX());
        homesConfig.set(path + ".y", location.getY());
        homesConfig.set(path + ".z", location.getZ());
        homesConfig.set(path + ".yaw", location.getYaw());
        homesConfig.set(path + ".pitch", location.getPitch());
        saveConfig();
    }

    public Location getHome(Player player, String name) {
        String path = "homes." + player.getUniqueId().toString() + "." + name;
        if (homesConfig.contains(path)) {
            String worldName = homesConfig.getString(path + ".world");
            double x = homesConfig.getDouble(path + ".x");
            double y = homesConfig.getDouble(path + ".y");
            double z = homesConfig.getDouble(path + ".z");
            float yaw = (float) homesConfig.getDouble(path + ".yaw");
            float pitch = (float) homesConfig.getDouble(path + ".pitch");
            return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
        }
        return null;
    }

    public void deleteHome(Player player, String name) {
        String path = "homes." + player.getUniqueId().toString() + "." + name;
        homesConfig.set(path, null);
        saveConfig();
    }

    public Set<String> listHomes(Player player) {
        String path = "homes." + player.getUniqueId().toString();
        return homesConfig.getConfigurationSection(path).getKeys(false);
    }

    void saveConfig() {
        try {
            homesConfig.save(homesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
