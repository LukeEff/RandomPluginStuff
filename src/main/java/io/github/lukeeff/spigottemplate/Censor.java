package io.github.lukeeff.spigottemplate;

import io.github.lukeeff.spigottemplate.wrappers.DolphinEntityTypeWrapper;
import io.github.lukeeff.spigottemplate.wrappers.DolphinTypeWrapper;
import net.minecraft.server.v1_15_R1.EnumMobSpawn;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.block.CraftCreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class Censor implements Listener {

    private final HUDLearning plugin;
    private final FileConfiguration config;
    private List<String> bannedWords;

    public Censor(HUDLearning instance) {
        plugin = instance;
        config = instance.getConfig();
        bannedWords = config.getStringList("Banned_Words");
        Player p;
        //Location location = p.getLocation().getBlock().getState().getClass().asSubclass()
        //p.getLocation().getBlock().setType(Material.ACACIA_BOAT);
        //((Chest) p.getLocation().getBlock().getState()).setLootTable();
    }

    /**
     * This method gets called when the player chats.
     *
     * @param event this is the event that we are listening to.
     */
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        final String playerMessage = event.getMessage(); //The chat message the player sent.
        final String censoredMessage = getCensoredMessage(playerMessage); //Gets the message censored
        event.setMessage(censoredMessage); //Sets the message.
    }

    /**
     * Sets the block broken by the player to a stronghold library chest.
     *
     * e.setCancelled(true) - call it or chest will be the block broken.
     *
     * @param e the block break event.
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        //e.getBlock().setType(Material.CHEST);
        //Chest chest = (Chest) e.getBlock().getState();
        ///chest.setLootTable(LootTables.STRONGHOLD_LIBRARY.getLootTable());
        // //chest.update();
       // e.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if(e.getBlock().getBlockData().getMaterial().equals(Material.DIAMOND_BLOCK)) {
            e.getBlock().setType(Material.SPAWNER);
            BlockState state = e.getBlock().getState();
            net.minecraft.server.v1_15_R1.World world = ((CraftWorld) e.getBlock().getWorld()).getHandle();
            //DolphinTypeWrapper.DOLPHIN_WRAPPER.spawnCreature(world, null, null, null, EnumMobSpawn.SPAWNER, false, false);
            //((CraftCreatureSpawner) state).setCreatureTypeByName(DolphinEntityTypeWrapper.nameKey);
            ((CraftCreatureSpawner) state).setSpawnedType(DolphinEntityTypeWrapper.WRAPPED_DOLPHIN);

                    //.setSpawnedType(DolphinEntityTypeWrapper.WRAPPED_DOLPHIN);
            //dolphin
            //e.getBlock().getState().getBlockData().
            state.update();

        }
    }

    @EventHandler
    public void onMobSpawnEvent(EntitySpawnEvent e) {

    }

    /**
     * Censors a message
     *
     * @param message the message
     * @return the censored message
     */
    private String getCensoredMessage(String message) {
        String censored = "";
        for(String word : message.split(" ")) {

            if(bannedWords.contains(word.toLowerCase())) {
                censored = censored.concat(StringUtils.repeat("*", word.length()));
            } else {
                censored = censored.concat(word);
            }
            censored = censored.concat(" ");
        }
        return censored;
    }



}
