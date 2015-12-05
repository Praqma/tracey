package net.praqma.tracey.protocol;

import net.praqma.tracey.protocol.Events.*;

import java.util.List;
import java.util.logging.Logger;

public class TestResultEventFactory extends EventFactory {
    private static final Logger log = Logger.getLogger( TestResultEventFactory.class.getName() );

    public static Event create(final TestResult testResult) {
        final Event.Builder event = prepareBaseEvent();
        event.setTestResult(testResult);

        return event.build();
    }

    public static Event create(final List<Reference> references, final TestResult testResult) {
        final Event.Builder event = prepareBaseEvent();
        event.addAllReferences(references);
        event.setTestResult(testResult);

        return event.build();
    }

    public static Event create(final Reference reference, final TestResult testResult) {
        final Event.Builder event = prepareBaseEvent();
        event.addReferences(reference);
        event.setTestResult(testResult);

        return event.build();
    }
}
