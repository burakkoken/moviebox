package org.codnect.moviebox.event.movie;

import org.codnect.moviebox.event.outbox.OutboxEvent;

public class MovieEvent extends OutboxEvent {

    public MovieEvent(Long aggregateId, String topic, Object source) {
        super(aggregateId, topic, source);
    }

}
