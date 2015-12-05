* Start Jenkins
  rm -rf work target && mvn hpi:run -Djetty.port=8090

* Go to Script console and run

import net.praqma.tracey.protocol.*;

EventFactory factory = new EventFactory();
def event = factory.create();
event.toString();