package net.praqma.tracey.protocol;

import jenkins.model.Jenkins;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class Source {
    private static final Logger log = Logger.getLogger( Source.class.getName() );
    private static final String DEFAULT = "undefined";
    private String hostName;
    private String resourceName;

    public Source() {
        this.hostName = retrieveHostName();
        this.resourceName = retrieveResourceName();
    }

    private String retrieveResourceName() {
        ProtocolConfiguration.DescriptorImpl descriptor = (ProtocolConfiguration.DescriptorImpl)
                Jenkins.getInstance().getDescriptor(ProtocolConfiguration.class);
        if (descriptor != null) {
            return descriptor.getResourceName() != null ? descriptor.getResourceName() : DEFAULT ;
        }
        return DEFAULT;
    }

    private String retrieveHostName() {
        String hostName;
        try {
            hostName = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            log.warning("Can't get hostname, will use '" + DEFAULT + "' instead. Error: " + e.getMessage());
            hostName = DEFAULT;
        }
        return hostName;
    }

    public String getHostName() {
        return hostName;
    }

    public String getResourceName() {
        return resourceName;
    }
}
