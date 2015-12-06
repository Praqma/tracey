#!/usr/bin/env bash

set -x

ROOTDIR=$(git rev-parse --show-toplevel)
DSL=$(find ${ROOTDIR}/jenkins/master -name dsl -type d)

if [ ! -d "${DSL}" ]
then
    echo "Can't find dsl directory"
    exit 1
fi

DSL_JAR=$(find ${ROOTDIR}/.tmp -name '*standalone.jar'|tail -1)

if [ ! -f "${DSL_JAR}" ]
then
  [ -e "${ROOTDIR}/.tmp" ] && rm -rf ${ROOTDIR}/.tmp
  mkdir ${ROOTDIR}/.tmp
  git clone https://github.com/jenkinsci/job-dsl-plugin.git ${ROOTDIR}/.tmp/jobdsl
  cd ${ROOTDIR}/.tmp/jobdsl
  ./gradlew :job-dsl-core:oneJar
  cd -
fi

DSL_JAR=$(find ${ROOTDIR}/.tmp -name '*standalone.jar'|tail -1)

if [ -f "${DSL_JAR}" ]; then
   CLASSPATH="$DSL_JAR:${DSL}/jobhelpers"
   java -cp "$CLASSPATH" OneJar ${DSL}/*.dsl > xmlbuild.log
else
   echo No JobDSL.jar built.
   exit 1
fi