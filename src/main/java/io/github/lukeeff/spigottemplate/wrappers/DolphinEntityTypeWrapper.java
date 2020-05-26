package io.github.lukeeff.spigottemplate.wrappers;

import lombok.SneakyThrows;
import net.minecraft.server.v1_15_R1.Entity;
import net.minecraft.server.v1_15_R1.EntityTypes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import sun.reflect.ConstructorAccessor;

import java.lang.reflect.*;
import java.util.Map;

public class DolphinEntityTypeWrapper {

    //EntityType wrappedDolphin = EntityType.DOLPHIN;
    public static String nameKey = "wdolphin";
    public static final EntityType WRAPPED_DOLPHIN = registerNewEntityType(nameKey, DolphinWrapper.class, -1);


    static {
        register();
    }

    public static void register() {
        Field nameMapField = null;
        //Field idMapField = null;
        try {
            nameMapField = EntityType.class.getDeclaredField("NAME_MAP");
            //idMapField = EntityType.class.getDeclaredField("ID_MAP");
            nameMapField.setAccessible(true);
            //idMapField.setAccessible(true);

            Field modifiers = Field.class.getDeclaredField("modifiers");
            modifiers.setAccessible(true);

            modifiers.setInt(nameMapField, nameMapField.getModifiers() & ~Modifier.FINAL);
            Map<String, EntityType> newTypeMap = (Map<String, EntityType>) nameMapField.get(null);
            newTypeMap.put(nameKey, WRAPPED_DOLPHIN);
            nameMapField.set(null, newTypeMap);


        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static EntityType registerNewEntityType(String key, Class<? extends Entity> type, int typeId) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Registering wrapper...");
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

            EntityType enumValue = (EntityType) ca.newInstance(new Object[] {key, EntityType.values().length + 1, key, type, typeId});
            //Field vals = entityTypeClass.getDeclaredField("$VALUES");

            Object[] vals = EntityType.class.getDeclaredFields();

            for(int i = 0; i < vals.length; i++ ) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "" + vals[i]);

            }

            //Field valuesField = EntityType.values().getClass().getDeclaredField("$VALUES");
            Field valuesField = EntityType.class.getDeclaredField("ENUM$VALUES");
            makeAccessible(valuesField);
            // just copy old values to new array and add our new field.
            EntityType[] oldValues = (EntityType[]) valuesField.get(null);
            EntityType[] newValues = new EntityType[oldValues.length + 1];
            System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
            newValues[oldValues.length] = enumValue;
            valuesField.set(null, newValues);

            return enumValue;

        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "Something went wrong.");
        return null;
    }



    public static void makeAccessible(Field field) throws NoSuchFieldException, IllegalAccessException {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~ Modifier.FINAL);
    }

}
