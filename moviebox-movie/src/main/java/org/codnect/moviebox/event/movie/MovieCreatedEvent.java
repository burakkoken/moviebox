package org.codnect.moviebox.event.movie;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codnect.moviebox.dto.MovieDTO;
import org.codnect.moviebox.model.Movie;

public class MovieCreatedEvent extends MovieEvent {

    public static String TOPIC_NAME = "MovieCreatedEvent";

    private static ObjectMapper MAPPER = new ObjectMapper();

    public MovieCreatedEvent(Long movieId, Movie movie) {
        super(movieId, TOPIC_NAME, getMessage(movie));
    }

    private static Object getMessage(Movie movie) {
        return MAPPER.createObjectNode()
                .put("movieId", movie.getId())
                .put("movieTitle", movie.getTitle())
                .put("movieReleaseDate", movie.getReleaseDate().getTime());
    }

}
