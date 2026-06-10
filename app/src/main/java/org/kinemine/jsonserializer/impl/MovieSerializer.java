package org.kinemine.jsonserializer.impl;

import org.kinemine.model.Movie;

import org.kinemine.jsonserializer.JsonSerializer;
import org.kinemine.jsonserializer.SerializerRegistry;
import org.kinemine.jsonserializer.CompoundJsonBuilder;

public class MovieSerializer implements JsonSerializer<Movie> {

    @Override
    public String serialize(Movie movie) {
        if (movie == null) return "null";
        JsonSerializer<String> stringSerializer
            = SerializerRegistry.getSerializer(String.class); 
        return new CompoundJsonBuilder()
            .addKeyValue(
                "\"title\"",
                stringSerializer.serialize(movie.title)
            )
            .addKeyValue(
                "\"imageUrl\"",
                stringSerializer.serialize(movie.imageUrl)
            ).getProduct();
    }

    public Class<Movie> getSupportedType() {
        return Movie.class;
    }

}
