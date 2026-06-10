package org.kinemine.jsonserializer.impl;

import org.kinemine.jsonserializer.JsonSerializer;

public class BooleanSerializer implements JsonSerializer<Boolean> {

	@Override
	public String serialize(Boolean argument) {
        if (argument == null) return "null"; 
        return argument.booleanValue() ? "true" : "false";
	}

	@Override
	public Class<Boolean> getSupportedType() {
        return Boolean.class;
	}
    
}
