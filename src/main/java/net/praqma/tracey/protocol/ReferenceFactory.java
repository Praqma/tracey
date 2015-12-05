package net.praqma.tracey.protocol;

import net.praqma.tracey.protocol.Events.*;

import java.util.logging.Logger;

public class ReferenceFactory {
    private static final Logger log = Logger.getLogger( ReferenceFactory.class.getName() );

    private static Reference.Builder prepareReference(Event event) {
        final Reference.Builder reference = Reference.newBuilder();
        reference.setId(event.getEventId());
        return reference;
    }

    public static Reference createAncestorReference(Event event) {
        return prepareReference(event).setType(Reference.ReferenceType.ANCESTOR).build();
    }

    public static Reference createReasonReference(Event event) {
        return prepareReference(event).setType(Reference.ReferenceType.REASON).build();
    }

    public static Reference createContainsReference(Event event) {
        return prepareReference(event).setType(Reference.ReferenceType.CONTAINS).build();
    }

    public static Reference createTestReference(Event event) {
        return prepareReference(event).setType(Reference.ReferenceType.TESTS).build();
    }

    public static Reference createReference(Event event, Reference.ReferenceType type) {
        return prepareReference(event).setType(type).build();
    }
}

