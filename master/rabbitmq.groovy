import java.lang.System
import hudson.model.*
import jenkins.model.*
import org.jenkinsci.tracey.*

def home_dir = System.getenv("JENKINS_HOME")
def properties = new ConfigSlurper().parse(new File("$home_dir/jenkins.properties").toURI().toURL())

properties.rabbitmq.each() { configName, serverConfig ->
  if(serverConfig.enabled) {
    println "--> Configure RabbitMQ: Server ${configName}"
    def servers = TraceyGlobalConfig.get().getConfiguredHosts()
    RabbitMQHost server = new RabbitMQHost(serverConfig.host,
      serverConfig.credentialsId,
      serverConfig.description,
      serverConfig.port,
      serverConfig.hostId)
    println "--> Tracey host server created"    
    if (servers == null || servers.empty) {
      servers = [server]
    } else {
      servers.push(server)
    }
    TraceyGlobalConfig.get().setConfiguredHosts(servers)
    TraceyGlobalConfig.get().save()
  }
}