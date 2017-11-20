#!/bin/bash
set -xe

# You can run it from any directory.
PROJECT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

"$PROJECT_DIR"/gradlew -Dbintray_email=$1 -Dbintray_user=$2 -Dbintray_key=$3 bintrayUpload