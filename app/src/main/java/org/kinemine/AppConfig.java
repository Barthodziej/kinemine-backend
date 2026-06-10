package org.kinemine;

import org.kinemine.jsonserializer.SerializerRegistry;
import org.kinemine.jsonserializer.impl.BooleanSerializer;
import org.kinemine.jsonserializer.impl.StringSerializer;
import org.kinemine.jsonserializer.impl.BufferedImageSerializer;
import org.kinemine.jsonserializer.impl.MovieSerializer;
import org.kinemine.jsonserializer.impl.ListSerializer;

public class AppConfig {
    
    public static void initializeRegistry() {
        SerializerRegistry.register(new BooleanSerializer());
        SerializerRegistry.register(new StringSerializer());
        SerializerRegistry.register(new BufferedImageSerializer());
        SerializerRegistry.register(new MovieSerializer());
        SerializerRegistry.register(new ListSerializer());
    }

}
