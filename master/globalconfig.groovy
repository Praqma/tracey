import java.lang.System
import hudson.model.*
import jenkins.model.*
import java.net.InetAddress

// load helpers and read properties
def home_dir = System.getenv("JENKINS_HOME")
GroovyShell shell = new GroovyShell()
def helpers = shell.parse(new File("$home_dir/init.groovy.d/helpers.groovy"))
def properties = new ConfigSlurper().parse(new File("$home_dir/jenkins.properties").toURI().toURL())

println "--> set number of executors on master to ${properties.global.numExecutorsOnMaster}"
Jenkins.instance.setNumExecutors(properties.global.numExecutorsOnMaster)

println "--> set quite period to ${properties.global.scmQuietPeriod}"
Jenkins.instance.setQuietPeriod(properties.global.scmQuietPeriod)

println "--> set checkout retry to ${properties.global.scmCheckoutRetryCount}"
Jenkins.instance.setScmCheckoutRetryCount(properties.global.scmCheckoutRetryCount)

// Change it to the DNS name if you have it
jlc = JenkinsLocationConfiguration.get()
if (properties.global.jenkinsRootUrl) {
  println "--> set jenkins root url to ${properties.global.jenkinsRootUrl}"
  jlc.setUrl(properties.global.jenkinsRootUrl)
} else {
  def ip = InetAddress.localHost.getHostAddress()
  println "--> set jenkins root url to ${ip}"
  jlc.setUrl("http://$ip:8080")
}
jlc.save()

// Set Admin Email as a string "Name <email>"
if (properties.global.jenkinsAdminEmail) {
  def jlc = JenkinsLocationConfiguration.get()
  println "--> set admin e-mail address to ${properties.global.jenkinsAdminEmail}"
  jlc.setAdminAddress(properties.global.jenkinsAdminEmail)
  jlc.save()
}

println "--> Set Global GIT configuration name to ${properties.global.git.name} and email address to ${properties.global.git.email}"
def inst = Jenkins.getInstance()
def desc = inst.getDescriptor("hudson.plugins.git.GitSCM")
desc.setGlobalConfigName(properties.global.git.name)
desc.setGlobalConfigEmail(properties.global.git.email)

println "--> Set system message "
systemMessage = "This Jenkins instance generated from code.\n " +
                "Avoid any manual changes since they will be discarded with next deployment.\n\n" +
                "Update ${properties.global.variables.default_repo} to change configuration"
println "Set system message to:\n ${systemMessage}"
Jenkins.instance.setSystemMessage(systemMessage)

println "--> Set global env variables"
properties.global.variables.each { key, value ->
  helpers.addGlobalEnvVariable(Jenkins, key, value)
}
