package io.github.lukeeff.spigottemplate.wrappers;

import net.minecraft.server.v1_15_R1.Entity;
import org.bukkit.entity.EntityType;
import sun.reflect.ConstructorAccessor;

import java.lang.reflect.*;

/**
 * Responsible for registering the custom dolphin type so it can be used for mob spawners.
 *
 * @author lukeeff
 * @since 5/25/2020
 */
public class DolphinEntityTypeRegistry {

    public static EntityType WRAPPED_DOLPHIN;
    public static final String DOLPH_KEY = "wdolphin";

    /**
     * Call this before using WRAPPED_DOLPHIN or feel the wrath of NPE.
     */
    public static void init() {
        WRAPPED_DOLPHIN = registerNewEntityType(DolphinWrapper.class, -1);
        DolphinEntityTypesRegistry.init();
    }

    /**
     * A ton of reflection that has a lot of room for improvement, but I'll leave that to you :)
     * Basically, it calls the EntityType enum constructor and sets the field values so it can be recognized
     * as a valid entity type. I don't think it's possible to be made visible through EntityType so I had to put
     * it here lol and I don't know how possible it is to make a wrapper for all of these enums.
     *
     * * Field valuesField = EntityType.class.getDeclaredField("ENUM$VALUES") - Gets the enum.values() field.
     *
     * @param type type of entity to be registered.
     * @param typeId id of the type. I just set it to -1.
     * @return the EntityType handle for future reference.
     */
    public static EntityType registerNewEntityType(Class<? extends Entity> type, int typeId) {
        try {
            Class<EntityType> entityTypeClass = EntityType.class;

            Constructor<?> constructor = entityTypeClass.getDeclaredConstructors()[1];
            constructor.setAccessible(true);

            Field constructorAccessorField = Constructor.class.getDeclaredField("constructorAccessor");
            constructorAccessorField.setAccessible(true);

            ConstructorAccessor ca = (ConstructorAccessor) constructorAccessorField.get(constructor);

            if (ca == null) {
                Method acquireConstructorAccessorMethod = Constructor.class.getDeclaredMethod("acquireConstructorAccessor");
                acquireConstructorAccessorMethod.setAccessible(true);
                ca = (ConstructorAccessor) acquireConstructorAccessorMethod.invoke(constructor);
            }

            EntityType enumValue = (EntityType) ca.newInstance(new Object[] { DOLPH_KEY, EntityType.values().length + 1, DOLPH_KEY, type, typeId } );
            Field valuesField = EntityType.class.getDeclaredField("ENUM$VALUES");
            makeAccessible(valuesField);

            EntityType[] oldValues = (EntityType[]) valuesField.get(null);
            EntityType[] newValues = new EntityType[oldValues.length + 1];

            System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
            newValues[oldValues.length] = enumValue;
            valuesField.set(null, newValues);
            return enumValue;

        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Makes a field accessible.
     *
     * @param field the field.
     * @throws NoSuchFieldException  when no field.
     * @throws IllegalAccessException when illegal access.
     */
    public static void makeAccessible(Field field) throws NoSuchFieldException, IllegalAccessException {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~ Modifier.FINAL);
    }

}
