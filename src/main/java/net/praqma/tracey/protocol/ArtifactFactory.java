package net.praqma.tracey.protocol;

import net.praqma.tracey.protocol.Events.*;

import java.util.logging.Logger;

public class ArtifactFactory {
    private static final Logger log = Logger.getLogger( ArtifactFactory.class.getName() );

    public static Artifact create(final String group, final String id, final String version, final String promotionLevel) {
        final Artifact.Builder artifact = Artifact.newBuilder();
        artifact.setGroup(group).setId(id).setVersion(version).setPromotionLevel(promotionLevel);

        return artifact.build();
    }
}
