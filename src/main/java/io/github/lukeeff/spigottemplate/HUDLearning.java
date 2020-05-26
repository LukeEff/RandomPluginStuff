package io.github.lukeeff.spigottemplate;

import io.github.lukeeff.spigottemplate.wrappers.DolphinEntityTypeWrapper;
import io.github.lukeeff.spigottemplate.wrappers.DolphinTypeWrapper;
import io.github.lukeeff.spigottemplate.wrappers.DolphinWrapper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import static io.github.lukeeff.spigottemplate.wrappers.DolphinTypeWrapper.register;

public class HUDLearning extends JavaPlugin {

    //@Getter private ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Censor(this), this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Dolphin: " + DolphinEntityTypeWrapper.WRAPPED_DOLPHIN);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "DolphinWrapper: " + DolphinTypeWrapper.DOLPHIN_WRAPPER);

        //this.protocolManager = ProtocolLibrary.getProtocolManager();
        //IChunkAccess
        //Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Template successfully loaded!");

    }

    @Override
    public void onDisable() {

    }


}
