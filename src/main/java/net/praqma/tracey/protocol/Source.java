package net.praqma.tracey.protocol;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class Source {
    private static final Logger log = Logger.getLogger( Source.class.getName() );
    private static final String DEFAULT = "undefined";
    private String hostname;
    private String resourceName;

    public Source() {
        this.hostname = retrieveHostName();
        this.resourceName = DEFAULT;
    }

    public Source(final String resourceName) {
        this.hostname = retrieveHostName();
        this.resourceName = resourceName;
    }

    private String retrieveHostName() {
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            log.warning("Can't get hostname, will use '" + DEFAULT + "' instead. Error: " + e.getMessage());
            hostname = DEFAULT;
        }
        return hostname;
    }
}
