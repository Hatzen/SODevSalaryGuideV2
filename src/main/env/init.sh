#!/bin/sh -x
set -e

PROJECT_NAME="${1:-SoGuideLocal}"
docker-compose -p $PROJECT_NAME --env-file .\example.env up -d