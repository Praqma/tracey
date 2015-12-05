package net.praqma.tracey.protocol;

import net.praqma.tracey.protocol.Events.*;

// TODO: Create method that can extract all required info from job or git
public class SourceCodeChangeFactory {

    public static SourceCodeChange create(final String repo, final String id, final String title, final String author, final String wi) {
        final SourceCodeChange.Builder sourceCodeChange = SourceCodeChange.newBuilder();
        sourceCodeChange.setRepo(repo).setId(id).setTitle(title).setAuthor(author).setWorkItem(wi);

        return sourceCodeChange.build();
    }
}
