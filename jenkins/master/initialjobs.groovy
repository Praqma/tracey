import hudson.model.*
import jenkins.model.*
import hudson.slaves.*
import javaposse.jobdsl.plugin.*
import hudson.plugins.git.*
import java.util.Collections
import java.util.List
import hudson.security.ACL
import hudson.triggers.TimerTrigger
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import hudson.plugins.sshslaves.*

def jobName = 'seed-job'
def instance = Jenkins.getInstance()

// Remove seed job if already exists
job = Jenkins.instance.getJob(jobName)
if (job) {
  job.delete()
}

// Create seed-jod. The job will initiate all jobs from alljobs.dsl
def project = new FreeStyleProject(Jenkins.instance, jobName)
project.setAssignedLabel()

// Set gitlab get repository, branches and credentials 
def projectURL = "git@github.com:Andrey9kin/tracy.git"
// Just add url: 
// def scm = new GitSCM(projectURL) 
// Add also branch and credentialsId. 
List<BranchSpec> branches = Collections.singletonList(new BranchSpec("*/master"));
List<SubmoduleConfig> submoduleCnf = Collections.<SubmoduleConfig>emptyList();
// We are using predefined user id jenkins. You change it in the global config
List<UserRemoteConfig> usersconfig = Collections.singletonList(new UserRemoteConfig(projectURL, '', '', 'jenkins')) 
def scm = new GitSCM(usersconfig, branches, false, submoduleCnf, null, null, null)

project.setScm(scm)

// Get script execute from checked out git repository:
def jobDslBuildStep = new ExecuteDslScripts(scriptLocation=new ExecuteDslScripts.ScriptLocation(value = "false", targets="jenkins/dsl/jobs/*.dsl", scriptText=""),
                                            ignoreExisting=false,
                                            removedJobAction=RemovedJobAction.DELETE,
                                            removedViewAction=RemovedViewAction.DELETE,
                                            lookupStrategy=LookupStrategy.JENKINS_ROOT,
                                            additionalClasspath='jenkins/dsl/src/main/groovy/net/praqma/tracy/job');

project.getBuildersList().add(jobDslBuildStep)
project.addTrigger(new TimerTrigger("@midnight"))
project.save()
Jenkins.instance.reload()

// trigger jobs generation
job = Jenkins.instance.getJob(jobName)
hudson.model.Hudson.instance.queue.schedule(job)