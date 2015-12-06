import jenkins.model.*
import hudson.security.*

File passwordFile = new File('/var/jenkins_home/.ssh/.password')
if(passwordFile.exists() && !passwordFile.isDirectory()) {
  println "--> Enable security and create user"
  def instance = Jenkins.getInstance()

  def hudsonRealm = new HudsonPrivateSecurityRealm(false)
  hudsonRealm.createAccount("jenkins", passwordFile.text)
  instance.setSecurityRealm(hudsonRealm)

  def strategy = new GlobalMatrixAuthorizationStrategy()
  strategy.add(Jenkins.ADMINISTER, "jenkins")
  instance.setAuthorizationStrategy(strategy)

  instance.save()
}