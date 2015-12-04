import net.praqma.tracey.protocol.*;

BaseEvent event = new BaseEvent();

println event.getEventId()
println event.getTimestamp()
println event.getSource().getHostName()
println event.getSource().getResourceName()
println event.hasReferences()
println event.getReferences()