package org.kinemine.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TagRepository {

    Path repositoryPath;

    public TagRepository(Path repositoryPath) {
        this.repositoryPath = repositoryPath;
    }

    public List<String> getTags() throws IOException {
        if (!Files.exists(repositoryPath)) {
            System.err.println("File " + repositoryPath + " does not exist");
            return null;
        }
        if (!Files.isDirectory(repositoryPath)) {
            System.err.println("File " + repositoryPath + " does not represent a directory");
            return null;
        }
        Path tagsPath = repositoryPath.resolve("tags.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(tagsPath.toString()))) {
            return br.lines()
                .toList();
        }
    }
}
