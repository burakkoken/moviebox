package org.codnect.moviebox.event.outbox;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.context.ApplicationEvent;

public abstract class OutboxEvent extends ApplicationEvent {

    public OutboxEvent(JsonNode source) {
        super(source);
    }

    public abstract String getAggregateId();

    public abstract String getAggregateType();

    public abstract String getType();

    public JsonNode getPayload() {
        return (JsonNode) getSource();
    }

}
