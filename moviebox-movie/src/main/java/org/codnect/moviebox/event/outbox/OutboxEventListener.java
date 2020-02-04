package org.codnect.moviebox.event.outbox;

import org.codnect.moviebox.outbox.Outbox;
import org.codnect.moviebox.outbox.OutboxRepository;
import org.codnect.moviebox.outbox.OutboxStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OutboxEventListener implements ApplicationListener<OutboxEvent> {

    private OutboxRepository outboxRepository;

    @Autowired
    public OutboxEventListener(OutboxRepository outboxRepository) {
        this.outboxRepository = outboxRepository;
    }

    @Override
    public void onApplicationEvent(OutboxEvent outboxEvent) {
        Outbox outbox = new Outbox();
        outbox.setUuid( UUID.randomUUID());
        outbox.setAggregateId(outbox.getAggregateId());
        outbox.setTopic(outboxEvent.getTopicName());
        outbox.setPayload(outbox.getPayload());
        outbox.setStatus(OutboxStatus.UNPUBLISHED);
        outbox.setTimestamp(outboxEvent.getTimestamp());
        outboxRepository.save(outbox);
        //outboxRepository.delete(outbox);
    }

}
