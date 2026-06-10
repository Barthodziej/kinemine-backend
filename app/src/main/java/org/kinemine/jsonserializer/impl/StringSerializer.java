package org.kinemine.jsonserializer.impl;

import org.kinemine.jsonserializer.JsonSerializer;

public class StringSerializer implements JsonSerializer<String> {

    private static String escape(char character) {
        return switch (character) {
            case '"'  -> "\\\"";
            case '\\' -> "\\\\";
            case '/'  -> "\\/";
            case '\b' -> "\\b"; 
            case '\f' -> "\\f"; 
            case '\n' -> "\\n";
            case '\r' -> "\\r";
            case '\t' -> "\\t";
            default -> String.valueOf(character);
        };
        // todo: handle unicode;
    }

    @Override
    public String serialize(String string) {
        if (string == null) return "null";
        StringBuilder sb = new StringBuilder();
        sb.append('"');
        string.chars()
            .mapToObj(c -> (char) c)
            .map(StringSerializer::escape)
            .forEach(
                sb::append
            );
        sb.append('"');
        return sb.toString();
    }

    @Override
    public Class<String> getSupportedType() {
        return String.class;
    }

}
