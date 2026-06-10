package org.kinemine.repository;

import org.kinemine.model.Movie;
import org.kinemine.modelbuilder.MovieBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.imageio.ImageIO;

public class MovieRepository {

    Path repositoryPath;

    public MovieRepository(Path repositoryPath) {
        this.repositoryPath = repositoryPath;
    }

    private Movie getMovie(Path moviePath) throws IOException {
        if (!Files.exists(moviePath)) {
            System.err.println("File " + moviePath + " does not exist");
            return null;
        }
        if (!Files.isDirectory(moviePath)) {
            System.err.println("File " + moviePath + " does not represent a directory");
            return null;
        }
        Path titlePath = moviePath.resolve("title.txt");
        Path imageUrlPath = moviePath.resolve("image-url.txt");
        Path watchedPath = moviePath.resolve("watched.txt");
        String title;
        String imageUrl;
        boolean watched;
        try (BufferedReader br = new BufferedReader(new FileReader(titlePath.toString()))) {
            title = br.readLine();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(imageUrlPath.toString()))) {
            imageUrl = br.readLine();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(watchedPath.toString()))) {
            watched = br.readLine().equals("true") ? true : false;
        }
        return new MovieBuilder()
            .withTitle(title)
            .withImageUrl(imageUrl)
            .withWatched(watched)
            .getProduct();
    }

    public List<Movie> getAllMovies() throws IOException {
        if (!Files.exists(repositoryPath)) {
            System.err.println("File " + repositoryPath + " does not exist");
            return null;
        }
        if (!Files.isDirectory(repositoryPath)) {
            System.err.println("File " + repositoryPath + " does not represent a directory");
            return null;
        }
        try (var pathStream = Files.list(repositoryPath)) {
            return pathStream
                .map(path -> {
                    try {
                        return getMovie(path);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                })
                .toList();
        }
    }

    public List<Movie> getWatchedMovies() throws IOException {
        return getAllMovies().stream()
            .filter(movie -> movie.watched)
            .toList();
    }

    public List<Movie> getUnwatchedMovies() throws IOException {
        return getAllMovies().stream()
            .filter(movie -> !movie.watched)
            .toList();
    }
}
