package io.github.lukeeff.spigottemplate.wrappers;

import net.minecraft.server.v1_15_R1.*;

import java.util.Random;

public class DolphinWrapper extends EntityDolphin {

    /**
     * Constructor for wrapper.
     *
     * EntityTypes.PIG would make it a pig lol.
     *
     * @param entityTypes the type of entity it is.
     * @param world the world.
     */
    public DolphinWrapper(EntityTypes<? extends EntityDolphin> entityTypes, World world) {
        super(EntityTypes.DOLPHIN, world);
    }

    /**
     * Method that will be called for checking spawning conditions.
     *
     * Add your condition here :D
     *
     * @param entityTypes type of entity.
     * @param generatorAccess generatorAccess object handle.
     * @param enumMobSpawn how the mob was spawned (spawner, cmd, etc)
     * @param blockPosition block position.
     * @param random random.
     * @param <T> sub class of EntityInsentient.
     * @return true if the mob can spawn.
     */
    public static <T extends EntityInsentient> boolean canSpawn(EntityTypes<T> entityTypes, GeneratorAccess generatorAccess, EnumMobSpawn enumMobSpawn, BlockPosition blockPosition, Random random) {
        return true;
    }

}
