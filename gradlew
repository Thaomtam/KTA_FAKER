#!/usr/bin/env sh
set -e

DEFAULT_JVM_OPTS=""
JAVA_CMD="java"
JAR_PATH="./gradle/wrapper/gradle-wrapper.jar"

exec "$JAVA_CMD" ${DEFAULT_JVM_OPTS} -jar "$JAR_PATH" "$@"
