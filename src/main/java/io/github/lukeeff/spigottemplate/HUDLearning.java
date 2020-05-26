package io.github.lukeeff.spigottemplate;

import io.github.lukeeff.spigottemplate.wrappers.DolphinEntityTypeRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public class HUDLearning extends JavaPlugin {

    //@Getter private ProtocolManager protocolManager;

    public void init() {
        DolphinEntityTypeRegistry.init();
    }

    @Override
    public void onEnable() {
        init();
        getServer().getPluginManager().registerEvents(new Censor(this), this);
        //Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Dolphin: " + DolphinEntityTypeRegistry.WRAPPED_DOLPHIN);
        //Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "DolphinWrapper: " + DolphinTypesRegistry.WRAPPED_DOLPHIN_TYPE);

        //this.protocolManager = ProtocolLibrary.getProtocolManager();
        //IChunkAccess
        //Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Template successfully loaded!");

    }

    @Override
    public void onDisable() {

    }


}
