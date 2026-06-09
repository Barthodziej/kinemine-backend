package org.kinemine.handler;

import org.kinemine.jsonserializer.SerializerRegistry;
import org.kinemine.model.Movie;
import org.kinemine.repository.MovieRepository;

import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Collectors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MoviesListHandler implements HttpHandler {

    MovieRepository repo;

    public MoviesListHandler(MovieRepository repo) {
        this.repo = repo;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        var movieSerializer = SerializerRegistry.getSerializer(Movie.class);
        String response = repo.getMovies()
            .stream()
            .map(movieSerializer::serialize)
            .collect(Collectors.joining(",", "[", "]"));
        byte[] responseBytes = response.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, responseBytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }

    }

}
