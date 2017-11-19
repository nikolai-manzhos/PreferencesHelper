#!/bin/bash
set -xe

# You can run it from any directory.
PROJECT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

"$PROJECT_DIR"/gradlew --no-daemon bintrayUpload -DBINTRAY_EMAIL=$1 -DBINTRAY_USER=$2 -DBINTRAY_KEY=$3