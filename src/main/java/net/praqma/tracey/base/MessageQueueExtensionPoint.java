package net.praqma.tracey.base;

import hudson.ExtensionList;
import hudson.ExtensionPoint;
import jenkins.model.Jenkins;

public abstract class MessageQueueExtensionPoint implements ExtensionPoint {

    public static ExtensionList<MessageQueueExtensionPoint> all() {
        return Jenkins.getInstance().getExtensionList(MessageQueueExtensionPoint.class);
    }

    public abstract Boolean isAlive();
    public abstract void    send();
}