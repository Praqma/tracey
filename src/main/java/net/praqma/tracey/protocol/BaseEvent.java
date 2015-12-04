package net.praqma.tracey.protocol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class BaseEvent {
    private static final Logger log = Logger.getLogger( BaseEvent.class.getName() );
    private String          eventId;
    private long            timestamp;
    private Source          source;
    private List<Reference> references;
    private ObjectMapper    mapper = new ObjectMapper();

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
        return !references.isEmpty();
    }

    public List<Reference> getReferences() {
        return references;
    }

    public String toJson() throws JsonProcessingException {
        return mapper.writeValueAsString(this);
    }

    public String toPrettyJson() throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }
}
