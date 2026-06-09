package org.kinemine.jsonserializer;

public class CompoundJsonBuilder {

    StringBuilder stringBuilder;

    public CompoundJsonBuilder() {
        stringBuilder = new StringBuilder();
        stringBuilder.append('{');
    }

    public CompoundJsonBuilder reset() {
        stringBuilder.setLength(0);
        stringBuilder.append('{');
        return this;
    }

    public String getProduct() {
        return stringBuilder
            .deleteCharAt(stringBuilder.length() - 1)
            .append('}')
            .toString();
    }

    public CompoundJsonBuilder addKeyValue(String key, String value) {
        stringBuilder
            .append(key)
            .append(':')
            .append(value)
            .append(',');
        return this;
    }

}
