package org.kinemine.jsonserializer;

import java.util.HashMap;
import java.util.Map;

public class SerializerRegistry {

    private static final Map<Class<?>, JsonSerializer<?>> registry = new HashMap<>();

    public static <T> void register(JsonSerializer<T> serializer) {
        registry.put(serializer.getSupportedType(), serializer);
    }

    @SuppressWarnings("unchecked") 
    public static <T> JsonSerializer<T> getSerializer(Class<T> type) {
        JsonSerializer<?> serializer = registry.get(type);
        if (serializer == null) {
            throw new IllegalArgumentException("No strategy registered for type: " + type.getName());
        }
        return (JsonSerializer<T>) serializer;
    }

}
