package hszy.ydy.defender;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Defender extends JavaPlugin {
    static double dmg,undfd;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Defender Prepared");
        saveDefaultConfig();
        FileConfiguration c = getConfig();
        dmg = c.getDouble("reboundpercent");
        undfd = c.getDouble("undefendpercent");
        getServer().getPluginManager().registerEvents(new MyListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("finished");
    }
}
