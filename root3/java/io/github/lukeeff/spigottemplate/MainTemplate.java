package io.github.lukeeff.spigottemplate;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class MainTemplate extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Template successfully loaded!");
    }

    @Override
    public void onDisable() {

    }

}
