package org.codnect.moviebox.event.movie;

import com.fasterxml.jackson.databind.JsonNode;
import org.codnect.moviebox.event.outbox.OutboxEvent;

public abstract class MovieEvent extends OutboxEvent {

    private Long movieId;

    public MovieEvent(Long movieId, JsonNode jsonNode) {
        super(jsonNode);
        this.movieId = movieId;
    }

    @Override
    public String getAggregateId() {
        return String.valueOf(movieId);
    }

    @Override
    public String getAggregateType() {
        return "Movie";
    }

}
