package net.praqma.tracey.protocol;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BaseEvent {
    private String          eventId;
    private long            timestamp;
    private Source source;
    private List<Reference> references;

    public BaseEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = java.lang.System.currentTimeMillis();
        this.source = new Source();
        this.references = new ArrayList<Reference>();
    }

    public BaseEvent(Source source) {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = java.lang.System.currentTimeMillis();
        this.source = source;
        this.references = new ArrayList<Reference>();
    }

    public BaseEvent(Source source, List<Reference> references) {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = java.lang.System.currentTimeMillis();
        this.source = source;
        this.references = references;
    }

    public String getEventId() {
        return eventId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Source getSource() {
        return source;
    }

    public void addReference(Reference reference) {
        references.add(reference);
    }

    public Boolean hasReferences() {
        return references.isEmpty() ? false : true;
    }

    public List<Reference> getReferences() {
        return references;
    }
}
