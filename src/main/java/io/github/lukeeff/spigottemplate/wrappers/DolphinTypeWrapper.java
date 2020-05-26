package io.github.lukeeff.spigottemplate.wrappers;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;
import net.minecraft.server.v1_15_R1.*;
import net.minecraft.server.v1_15_R1.DataConverterTypes;

import java.util.Map;


public class DolphinTypeWrapper<T extends EntityDolphin> {

    public static final EntityTypes<EntityDolphin> DOLPHIN_WRAPPER = register(DolphinEntityTypeWrapper.nameKey, EntityTypes.a.a(DolphinWrapper::new, EnumCreatureType.WATER_CREATURE).a(0.9F, 0.6F));

    //<Entity>
    @SuppressWarnings("unchecked")
    public static <T extends EntityDolphin> EntityTypes<EntityDolphin> register(String key, EntityTypes.a<Entity> other) {
        MinecraftKey mcKey = MinecraftKey.a(DolphinEntityTypeWrapper.nameKey);
        register();
        return (EntityTypes) IRegistry.a(IRegistry.ENTITY_TYPE, mcKey.getKey(), other.a(key));
    }

    @SuppressWarnings("unchecked")
    public static void register() {
        Map<Object, Type<?>> dataTypes = (Map<Object, com.mojang.datafixers.types.Type<?>>)DataConverterRegistry.a()
                .getSchema(DataFixUtils.makeKey(SharedConstants.getGameVersion().getWorldVersion()))
                .findChoiceType(DataConverterTypes.ENTITY).types();
        MinecraftKey key = MinecraftKey.a(DolphinEntityTypeWrapper.nameKey);

        dataTypes.put(key.toString(), dataTypes.get(EntityTypes.DOLPHIN.h().toString().replace("entity/", "")));

    }



}
