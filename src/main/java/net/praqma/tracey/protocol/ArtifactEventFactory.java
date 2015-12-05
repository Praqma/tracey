package net.praqma.tracey.protocol;

import net.praqma.tracey.protocol.Events.*;

import java.util.List;
import java.util.logging.Logger;

public class ArtifactEventFactory extends EventFactory {
    private static final Logger log = Logger.getLogger( ArtifactEventFactory.class.getName() );

    public static Event create(final Artifact artifact) {
        final Event.Builder event = prepareBaseEvent();
        event.setArtifact(artifact);

        return event.build();
    }

    public static Event create(final List<Reference> references, final Artifact artifact) {
        final Event.Builder event = prepareBaseEvent();
        event.addAllReferences(references);
        event.setArtifact(artifact);

        return event.build();
    }

    public static Event create(final Reference reference, final Artifact artifact) {
        final Event.Builder event = prepareBaseEvent();
        event.addReferences(reference);
        event.setArtifact(artifact);

        return event.build();
    }
}
