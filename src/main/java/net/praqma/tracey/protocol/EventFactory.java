package net.praqma.tracey.protocol;

import jenkins.model.Jenkins;
import net.praqma.tracey.protocol.Events.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;
import java.util.logging.Logger;

public class EventFactory {
    private static final Logger log = Logger.getLogger( EventFactory.class.getName() );
    private static final String DEFAULT = "undefined";

    private static String getResourceNameOrNull() {
        String resourceName = null;
        ProtocolConfiguration.DescriptorImpl descriptor = (ProtocolConfiguration.DescriptorImpl)
                Jenkins.getInstance().getDescriptor(ProtocolConfiguration.class);
        if (descriptor != null) {
            resourceName = descriptor.getResourceName();
        }
        return resourceName;
    }

    private static String getHostNameOrNull() {
        try {
            return InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            log.warning("Can't get hostname, will use 'null' instead. Error: " + e.getMessage());
        }
        return null;
    }

    private static String getEventId() {
        return UUID.randomUUID().toString();
    }

    private static long getTimeStamp() {
        return java.lang.System.currentTimeMillis();
    }

    protected static Event.Builder prepareBaseEvent() {
        final Event.Builder event = Event.newBuilder();
        final Source.Builder source = Source.newBuilder();

        source.setHostName(getHostNameOrNull() != null ? getHostNameOrNull() : DEFAULT);
        source.setResourceName(getResourceNameOrNull() != null ? getResourceNameOrNull() : DEFAULT);
        event.setSource(source.build());

        event.setEventId(getEventId());
        event.setTimeStamp(getTimeStamp());

        return event;
    }
}
