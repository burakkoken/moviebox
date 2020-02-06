package org.codnect.moviebox.event.movie;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codnect.moviebox.model.Movie;

public class MovieUpdatedEvent extends MovieEvent {

    private static ObjectMapper MAPPER = new ObjectMapper();

    public MovieUpdatedEvent(Long movieId, JsonNode jsonNode) {
        super(movieId, jsonNode);
    }

    public static MovieUpdatedEvent of(Long movieId, Movie movie) {
        JsonNode jsonNode = MAPPER.createObjectNode()
                .put("id", movie.getId())
                .put("title", movie.getTitle())
                .put("runtime", movie.getRuntime())
                .put("overview", movie.getOverview())
                .put("imdb", movie.getImdb())
                .put("releaseDate", movie.getReleaseDate().getTime());
        return new MovieUpdatedEvent(movieId, jsonNode);
    }

    @Override
    public String getType() {
        return "MovieUpdatedEvent";
    }

}