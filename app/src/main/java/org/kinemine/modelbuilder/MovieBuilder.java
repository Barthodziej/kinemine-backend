package org.kinemine.modelbuilder;

import org.kinemine.model.Movie;

public class MovieBuilder {

    private Movie movie;

    public MovieBuilder() {
        reset();
    }

    public MovieBuilder reset() {
        this.movie = new Movie();
        return this;
    }

    public Movie getProduct() {
        return movie;
    }

    public MovieBuilder withTitle(String title) {
        movie.title = title;
        return this;
    }

    public MovieBuilder withImageUrl(String imageUrl) {
        movie.imageUrl = imageUrl;
        return this;
    }

    public MovieBuilder withWatched(boolean watched) {
        movie.watched = watched;
        return this;
    }

    public MovieBuilder withTag(String tag) {
        movie.tags.add(tag);
        return this;
    }
 
}
