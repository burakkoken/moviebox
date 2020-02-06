package org.codnect.moviebox.event.movie;

import com.fasterxml.jackson.databind.JsonNode;
import org.codnect.moviebox.model.Movie;

public class MovieDeletedEvent extends MovieEvent {

    public MovieDeletedEvent(Long movieId, JsonNode jsonNode) {
        super(movieId, jsonNode);
    }

    public static MovieDeletedEvent of(Long movieId) {
        return new MovieDeletedEvent(movieId, null);
    }

    @Override
    public String getType() {
        return "MovieDeletedEvent";
    }

}
