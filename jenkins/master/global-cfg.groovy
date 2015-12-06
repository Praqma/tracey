import hudson.model.*
import jenkins.model.*
import java.net.InetAddress
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import hudson.plugins.sshslaves.*

println "--> disabling master executors"
Jenkins.instance.setNumExecutors(0)

println "--> setting quite period to 3"
Jenkins.instance.setQuietPeriod(3)

println "--> setting checkout retry to 3"
Jenkins.instance.setScmCheckoutRetryCount(3)

// Change it to the DNS name if you have it
println "--> setting jenkins root url"
def ip = InetAddress.localHost.getHostAddress()
jlc = JenkinsLocationConfiguration.get()
jlc.setUrl("http://$ip:8080")
jlc.save()

println "--> Creating jenkins ssh credentials"
global = Domain.global()
credentials_store =Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()
credentials = new BasicSSHUserPrivateKey(
  CredentialsScope.GLOBAL,
  "jenkins",
  "jenkins",
  new BasicSSHUserPrivateKey.UsersPrivateKeySource(),
  "",
  "jenkins")
credentials_store.addCredentials(global, credentials)