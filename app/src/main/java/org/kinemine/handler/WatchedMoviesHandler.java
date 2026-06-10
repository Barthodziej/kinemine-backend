package org.kinemine.handler;

import org.kinemine.jsonserializer.SerializerRegistry;
import org.kinemine.repository.MovieRepository;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class WatchedMoviesHandler implements HttpHandler {

    MovieRepository repo;

    public WatchedMoviesHandler(MovieRepository repo) {
        this.repo = repo;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        var listSerializer = SerializerRegistry.getSerializer(List.class);
        String response = listSerializer.serialize(repo.getWatchedMovies());
        byte[] responseBytes = response.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, responseBytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }

    }

}
