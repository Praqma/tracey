FROM jenkins:1.651.1-alpine

ARG TRACEY_PLUGIN_VERSION
ENV TRACEY_PLUGIN_VERSION ${TRACEY_PLUGIN_VERSION:-c522ae5058}

# Get credetntials. This is a bad practice and this dir should be mounted as volume
# but for the demo it will do
COPY cred /var/jenkins_home/cred

# Install plugins
COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt

# Copy tracey plugin. We use wget instead of ADD to avoid permissions hell since files created by ADD will be created as root
RUN wget https://jitpack.io/com/github/praqma/tracey-jenkins-trigger-plugin/${TRACEY_PLUGIN_VERSION}/tracey-jenkins-trigger-plugin-${TRACEY_PLUGIN_VERSION}.hpi -O /usr/share/jenkins/ref/plugins/tracey.hpi

# Copy configuration scripts that will be executed by groovy
COPY *.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY *.properties $JENKINS_HOME/
