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
        String title;
        String imageUrl;
        try (BufferedReader br = new BufferedReader(new FileReader(titlePath.toString()))) {
            title = br.readLine();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(imageUrlPath.toString()))) {
            imageUrl = br.readLine();
        }
        return new MovieBuilder()
            .withTitle(title)
            .withImageUrl(imageUrl)
            .getProduct();
    }

    public List<Movie> getMovies() throws IOException {
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
}
