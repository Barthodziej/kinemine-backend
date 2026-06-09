package org.kinemine.jsonserializer;

public interface JsonSerializer<T> {
    String serialize(T argument);
    Class<T> getSupportedType();
}
