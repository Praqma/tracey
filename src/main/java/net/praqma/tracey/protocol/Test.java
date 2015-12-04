package net.praqma.tracey.protocol;

import hudson.model.Result;

import java.util.List;

public class Test extends BaseEvent {
    private String description;
    private Result result;

    public Test(String description, Result result) {
        this.description = description;
        this.result = result;
    }

    public Test(Source source, String description, Result result) {
        super(source);
        this.description = description;
        this.result = result;
    }

    public Test(Source source, List<Reference> references, String description, Result result) {
        super(source, references);
        this.description = description;
        this.result = result;
    }
}
