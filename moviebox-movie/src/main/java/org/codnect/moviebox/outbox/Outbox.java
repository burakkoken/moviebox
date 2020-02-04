package org.codnect.moviebox.outbox;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class Outbox {

    @Id
    private UUID uuid;

    @Column
    private Long aggregateId;

    @Column
    private String topic;

    @Lob
    @Column(length = 1048576)
    private String payload;

    @Column
    @Enumerated(EnumType.STRING)
    private OutboxStatus status;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Long timestamp;

    public Outbox() {

    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Long getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(Long aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public OutboxStatus getStatus() {
        return status;
    }

    public void setStatus(OutboxStatus outboxStatus) {
        this.status = outboxStatus;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
