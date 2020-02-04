package org.codnect.moviebox.event.outbox;

import org.springframework.context.ApplicationEvent;

public abstract class OutboxEvent extends ApplicationEvent {

    private Long aggregateId;
    private String topic;

    public OutboxEvent(Long aggregateId, String topic, Object source) {
        super(source);
        this.aggregateId = aggregateId;
        this.topic = topic;
    }

    public Long getAggregateId() {
        return aggregateId;
    }

    public String getTopicName() {
        return topic;
    }

}
