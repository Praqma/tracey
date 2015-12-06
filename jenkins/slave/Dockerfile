FROM java:8u45-jdk

# Add support for proxies.
# Values should be passed as build args
# http://docs.docker.com/engine/reference/builder/#arg
ENV http_proxy ${http_proxy:-}
ENV https_proxy ${https_proxy:-}
ENV no_proxy ${no_proxy:-}
ENV HTTP_PROXY ${HTTP_PROXY:-}
ENV HTTPS_PROXY ${HTTPS_PROXY:-}
ENV NO_PROXY ${NO_PROXY:-}

# Make sure that this version is the same as swarm plugin in master/plugin.txt
ENV JENKINS_SWARM_VERSION 2.0
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
ENV JENKINS_HOME /var/jenkins_home

# Jenkins is run with user `jenkins`, uid = 1000
# If you bind mount a volume from the host or a data container,
# ensure you use the same uid
RUN useradd -d "$JENKINS_HOME" -u 1000 -m -s /bin/bash jenkins

# install netstat to allow connection health check with
# netstat -tan | grep ESTABLISHED
RUN apt-get update
RUN apt-get install -y net-tools

# Install tools required for the jobs
RUN apt-get install -y git maven

RUN curl --create-dirs -sSLo /usr/share/jenkins/swarm-client-$JENKINS_SWARM_VERSION-jar-with-dependencies.jar http://maven.jenkins-ci.org/content/repositories/releases/org/jenkins-ci/plugins/swarm-client/$JENKINS_SWARM_VERSION/swarm-client-$JENKINS_SWARM_VERSION-jar-with-dependencies.jar \
  && chmod 755 /usr/share/jenkins

COPY start.sh /usr/local/bin/start.sh
RUN chown jenkins /usr/local/bin/start.sh
RUN chmod 755 /usr/local/bin/start.sh

ENV JENKINS_PORT_8080_TCP_ADDR master
ENV JENKINS_PORT_8080_TCP_PORT 8080

USER jenkins

ENTRYPOINT ["/usr/local/bin/start.sh"]