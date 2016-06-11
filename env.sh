#!/bin/bash

#this is the source script.
#
# *** NOTE: FROM ES
# *** THIS SCRIPT USES artifactory branch of es/maven
#


start_dir=$(pwd)

hash git 2>&- || {
    echo >&2
    echo >&2 "*** Error: 'git' is required but not installed. \
Please install from http://github.roving.com"
    echo >&2

    return 1
}

# Absolute path this script is in.
script_dir=$(cd "$(dirname "$BASH_SOURCE")" && pwd)

if [ ! -d ${script_dir}/maven ]; then
    echo
    echo "Pulling down ES/maven  ..."

    cd ${script_dir}
    git clone git://github.roving.com/ES/maven.git ./maven
fi
cd ${script_dir}/maven
git checkout master
git pull

echo
echo "Setting up your build environment ..."

# Finally, setup the environment for running builds
# with ES maven.
export MVN_VERSION="3.2.5"
source ${script_dir}/maven/env/env.sh

cd ${start_dir}
return 0