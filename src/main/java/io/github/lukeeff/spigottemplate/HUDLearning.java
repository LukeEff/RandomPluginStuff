package io.github.lukeeff.spigottemplate;

import io.github.lukeeff.spigottemplate.wrappers.DolphinEntityTypeRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public class HUDLearning extends JavaPlugin {

    //@Getter private ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        DolphinEntityTypeRegistry.init();
        getServer().getPluginManager().registerEvents(new Censor(this), this);
    }

    @Override
    public void onDisable() {

    }


}
