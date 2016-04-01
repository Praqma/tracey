### More info

* SoW http://www.josra.org/sow/tracey.html
* TBD


### How to test

* Start Jenkins

```
rm -rf work target && mvn hpi:run -Djetty.port=8090
```

* Go to Script console and run

```
import net.praqma.tracey.protocol.Events.TestResult.TestResultType;
import net.praqma.tracey.protocol.Events.*;
import net.praqma.tracey.protocol.*;

def artifact = ArtifactFactory.create("net.praqma", "tracey", "1.0.0", "untested");
def testResult = TestResultFactory.create("superTest", TestResultType.UNSTABLE);
def sourceCodeChange = SourceCodeChangeFactory.create("repo", "sha1", "my commit", "me", "jira-12345");

def sccEvent = SourceCodeChangeEventFactory.create(sourceCodeChange);
def reference = ReferenceFactory.createReasonReference(sccEvent)

def artifactEvent = ArtifactEventFactory.create(reference, artifact)
reference = ReferenceFactory.createReasonReference(artifactEvent)

def testResultEvent = TestResultEventFactory.create(reference, testResult)

println "---------"
println sccEvent.toString();
println "---------"
println artifactEvent.toString();
println "---------"
println testResultEvent.toString();
println "---------"
