package org.kinemine.jsonserializer.impl;

import java.util.List;

import org.kinemine.jsonserializer.JsonSerializer;
import org.kinemine.jsonserializer.SerializerRegistry;

public class ListSerializer implements JsonSerializer<List<?>> {

    @Override
    public String serialize(List<?> list) {
        if (list == null) return "null";
        if (list.isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            Object item = list.get(i);
            if (item == null) {
                sb.append("null");
            }
            else {
                @SuppressWarnings("unchecked")
                JsonSerializer<Object> serializer = (JsonSerializer<Object>) SerializerRegistry.getSerializer(item.getClass());
                
                if (serializer != null) {
                    sb.append(serializer.serialize(item));
                }
                else {
                    throw new RuntimeException("Type" + item.getClass() + "does not have a registered JsonSerializer");
                }
            }
            sb.append(',');
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString(); 
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<List<?>> getSupportedType() {
        return (Class<List<?>>) (Class<?>) List.class;
    }

}
