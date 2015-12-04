package net.praqma.tracey.protocol;

public enum ReferenceType {
    ANCESTOR, // Previous of the same type
    REASON,   // Previous in the same delivery pipeline, i.e. cause for this event
    CONTAINS  // Aggregation
}

