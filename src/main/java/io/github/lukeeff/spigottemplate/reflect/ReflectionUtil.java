package io.github.lukeeff.spigottemplate.reflect;

import java.lang.reflect.Field;

public class ReflectionUtil {

    private ReflectionUtil() {}

    public static Object getPrivateField(String fieldName, Class<?> clazz, Object object) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch(NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
