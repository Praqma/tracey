FROM jenkins:1.651.1-alpine

# Get credetntials. This is a bad practice and this dir should be mounted as volume
# but for the demo it will do
COPY cred /var/jenkins_home/cred

# Install plugins
COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt

# Copy tracey plugin
COPY tracey.hpi /usr/share/jenkins/ref/plugins/

# Copy configuration scripts that will be executed by groovy
COPY *.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY *.properties $JENKINS_HOME/
