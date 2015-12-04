package net.praqma.tracey.protocol;

public class Reference {
    private ReferenceType type;
    private String id;

    public Reference (final ReferenceType type, final String id) {
        this.type = type;
        this.id = id;
    }

    public ReferenceType getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
