package org.codnect.moviebox.event.movie;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codnect.moviebox.dto.MovieDTO;

public class MovieCreatedEvent extends MovieEvent {

    public static String TOPIC_NAME = "MovieCreatedEvent";

    private static ObjectMapper MAPPER = new ObjectMapper();

    public MovieCreatedEvent(Long movieId, MovieDTO movieDTO) {
        super(movieId, TOPIC_NAME, getMessage(movieDTO));
    }

    private static Object getMessage(MovieDTO movieDTO) {
        return MAPPER.convertValue(movieDTO, JsonNode.class);
    }

}
