package io.github.lukeeff.spigottemplate.wrappers;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;
import net.minecraft.server.v1_15_R1.*;
import net.minecraft.server.v1_15_R1.DataConverterTypes;

import java.util.Map;

public class DolphinEntityTypesRegistry {

    public static EntityTypes<EntityDolphin> WRAPPED_DOLPHIN_TYPE;

    public static void init() {
        WRAPPED_DOLPHIN_TYPE = register(EntityTypes.a.a(DolphinWrapper::new,
                EnumCreatureType.WATER_CREATURE).a(0.9F, 0.6F));
    }

    /**
     * Registers the Dolphin EntityType.
     *
     * @param type type or something
     * @return the entityType.
     */
    @SuppressWarnings("unchecked")
    public static EntityTypes<EntityDolphin> register(EntityTypes.a<Entity> type) {
        final MinecraftKey mcKey = toMinecraftKey();
        register(mcKey);
        return (EntityTypes) IRegistry.a(IRegistry.ENTITY_TYPE, mcKey.getKey(), type.a(DolphinEntityTypeRegistry.DOLPH_KEY));
    }

    /**
     * Registers the key in the entity_tree. Required or you will get a lovely stack trace :D
     *
     * @param key the MinecraftKey that maps to the object.
     */
    @SuppressWarnings("unchecked")
    public static void register(MinecraftKey key) {
        Map<Object, Type<?>> dataTypes = (Map<Object, com.mojang.datafixers.types.Type<?>>)DataConverterRegistry.a()
                .getSchema(DataFixUtils.makeKey(SharedConstants.getGameVersion().getWorldVersion()))
                .findChoiceType(DataConverterTypes.ENTITY).types();

        dataTypes.put(key.toString(), dataTypes.get(EntityTypes.DOLPHIN.h().toString().replace("entity/", "")));
    }

    /**
     * Gets a MinecraftKey from a the dolphin key.
     *
     * @return the MinecraftKey
     */
    private static MinecraftKey toMinecraftKey() {
        return MinecraftKey.a(DolphinEntityTypeRegistry.DOLPH_KEY);
    }



}
