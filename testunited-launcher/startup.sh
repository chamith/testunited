#!/bin/bash

echo "Arg 1: $1"
echo "Arg 2: $2"
java -cp .:/app/app.jar org.testunited.launcher.TestArtifactManager $1 $2
java -cp .:/app/test-jars/*:/app/app.jar org.testunited.launcher.TestUnitedTestLauncher $1 $2