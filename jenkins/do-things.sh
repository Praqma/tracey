#!/usr/bin/env sh

set -ex

docker-compose build
docker-compose up -d

