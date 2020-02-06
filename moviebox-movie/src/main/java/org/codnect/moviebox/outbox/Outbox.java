package org.codnect.moviebox.outbox;

import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class Outbox {

    @Id
    private UUID uuid;

    @Column
    private String aggregateId;

    @Column
    private String aggregateType;

    @Column
    private String type;

    @Lob
    @Column(length = 1048576)
    private String payload;

    @Column
    @Enumerated(EnumType.STRING)
    private OutboxStatus status;

    @Column
    private Long timestamp;

    public Outbox() {

    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public void setAggregateType(String aggregateType) {
        this.aggregateType = aggregateType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
