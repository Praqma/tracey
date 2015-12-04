package net.praqma.tracey.protocol;

import java.util.List;

public class Artifact extends BaseEvent {
    private ArtifactType type;
    private String uri;     // downloadable link
    private String group;   // in case of binary actual group, in case of source whatever
    private String id;      // artifact name, in case of source repository or vob
    private String version; // sha1, label, artifact version
    private String description;


    public Artifact(ArtifactType type, String uri, String group, String id, String version, String description) {
        this.type = type;
        this.uri = uri;
        this.group = group;
        this.id = id;
        this.version = version;
        this.description = description;
    }

    public Artifact(Source source, ArtifactType type, String uri, String group, String id, String version, String description) {
        super(source);
        this.type = type;
        this.uri = uri;
        this.group = group;
        this.id = id;
        this.version = version;
        this.description = description;
    }

    public Artifact(Source source, List<Reference> references, ArtifactType type, String uri, String group, String id, String version, String description) {
        super(source, references);
        this.type = type;
        this.uri = uri;
        this.group = group;
        this.id = id;
        this.version = version;
        this.description = description;
    }

    public ArtifactType getType() {
        return type;
    }

    public String getUri() {
        return uri;
    }

    public String getGroup() {
        return group;
    }

    public String getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }
}
