package io.github.lukeeff.spigottemplate.wrappers;

import net.minecraft.server.v1_15_R1.*;

import java.util.Random;

public class DolphinWrapper extends EntityDolphin {

    public DolphinWrapper(EntityTypes<? extends EntityDolphin> entityTypes, World world) {
        super(EntityTypes.DOLPHIN, world);
    }

    //public static boolean predicate(EntityTypes<EntityDolphin> dolphinEntityTypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
    //    return true;
    //}

    public static <T extends EntityInsentient> boolean predicate(EntityTypes<T> entityTypes, GeneratorAccess generatorAccess, EnumMobSpawn enumMobSpawn, BlockPosition blockPosition, Random random) {
        return true;
    }

    //public static boolean b(EntityTypes<EntityDolphin> dolphinEntityTypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
     //   return blockposition.getY() > 45 && blockposition.getY() < generatoraccess.getSeaLevel() && (generatoraccess.getBiome(blockposition) != Biomes.OCEAN || generatoraccess.getBiome(blockposition) != Biomes.DEEP_OCEAN) && generatoraccess.getFluid(blockposition).a(TagsFluid.WATER);
    //}
}
