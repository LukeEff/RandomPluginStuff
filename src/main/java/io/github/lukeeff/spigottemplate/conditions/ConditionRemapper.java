package io.github.lukeeff.spigottemplate.conditions;

import io.github.lukeeff.spigottemplate.wrappers.DolphinEntityTypesRegistry;
import io.github.lukeeff.spigottemplate.wrappers.DolphinWrapper;
import net.minecraft.server.v1_15_R1.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConditionRemapper {

  //  public static final EntityType<Dol> DOLPHIN = register("dolphin", Builder.<DolphinEntity>create(DolphinEntity::new, EntityClassification.WATER_CREATURE).size(0.9F, 0.6F));


    public ConditionRemapper() {

    }

    private void remapDolphin() {

    }

    private void registerType() {
        //Method registerMethod = EntityTypes.class.getDeclaredMethod("")

    }

    @SuppressWarnings("")
    public static <T extends EntityInsentient> void handleReflect() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method registerMethod = EntityPositionTypes.class.getDeclaredMethod("a", EntityTypes.class, EntityPositionTypes.Surface.class, HeightMap.Type.class, EntityPositionTypes.b.class);
        registerMethod.invoke(null, DolphinEntityTypesRegistry.WRAPPED_DOLPHIN_TYPE, EntityPositionTypes.Surface.IN_WATER, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, (EntityPositionTypes.b<T>) DolphinWrapper::canSpawn);
        //registerMethod.invoke(EntityTypes.)

    }
    //a(EntityTypes.DOLPHIN, EntityPositionTypes.Surface.IN_WATER, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityDolphin::b);

}
