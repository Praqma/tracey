package net.praqma.tracey.protocol;

import java.util.List;
import java.util.logging.Logger;
import net.praqma.tracey.protocol.Events.*;

public class SourceCodeChangeEventFactory extends EventFactory {
    private static final Logger log = Logger.getLogger( SourceCodeChangeEventFactory.class.getName() );

    public static Event create(final SourceCodeChange sourceCodeChange) {
        final Event.Builder event = prepareBaseEvent();
        event.setSCChange(sourceCodeChange);

        return event.build();
    }
}
