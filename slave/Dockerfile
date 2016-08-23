FROM java:openjdk-8u77-alpine

ENV JENKINS_SWARM_VERSION 2.1
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk

# install git, curl, ssh, bash
RUN apk update && apk add git curl openssh bash

RUN curl --create-dirs -sSLo /usr/share/jenkins/swarm-client-$JENKINS_SWARM_VERSION-jar-with-dependencies.jar http://maven.jenkins-ci.org/content/repositories/releases/org/jenkins-ci/plugins/swarm-client/$JENKINS_SWARM_VERSION/swarm-client-$JENKINS_SWARM_VERSION-jar-with-dependencies.jar \
  && chmod 755 /usr/share/jenkins

#Install tool to do clean up of all unused docker layers and images
RUN curl -s https://raw.githubusercontent.com/Praqma/docker-clean/master/docker-clean | tee /usr/local/bin/docker-clean > /dev/null && chmod +x /usr/local/bin/docker-clean

COPY start.sh /usr/local/bin/start.sh
RUN chmod 755 /usr/local/bin/start.sh

ENTRYPOINT ["/usr/local/bin/start.sh"]