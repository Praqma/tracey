package net.praqma.tracey.protocol;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BaseEvent {
    private String          eventId;
    private long            timestamp;
    private Source          origin;
    private List<Reference> references;

    public BaseEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = java.lang.System.currentTimeMillis();
        this.origin = new Source();
        this.references = new ArrayList<Reference>();
    }

    public BaseEvent(Source source) {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = java.lang.System.currentTimeMillis();
        this.origin = source;
        this.references = new ArrayList<Reference>();
    }

    public BaseEvent(Source source, List<Reference> references) {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = java.lang.System.currentTimeMillis();
        this.origin = source;
        this.references = references;
    }

    public String getEventId() {
        return eventId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Source getOrigin() {
        return origin;
    }

    public void addReference(Reference reference) {
        references.add(reference);
    }

    public Boolean hasReferences() {
        return references.isEmpty();
    }

    public List<Reference> getReferences() {
        return references;
    }
}
