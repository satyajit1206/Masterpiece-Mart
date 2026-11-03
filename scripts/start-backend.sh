#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
# Prefer the user's JDK. This fallback supports the portable JDK prepared on this Mac.
LOCAL_JDK="$ROOT/../../work/tools/jdk-17.0.21+9/Contents/Home"
if [[ -z "${JAVA_HOME:-}" && -x "$LOCAL_JDK/bin/java" ]]; then export JAVA_HOME="$LOCAL_JDK"; fi
if [[ -d "$ROOT/../../work/maven" ]]; then
  export MAVEN_USER_HOME="$ROOT/../../work/maven"
  REPO_ARGS=("-Dmaven.repo.local=$MAVEN_USER_HOME/repository")
else
  REPO_ARGS=()
fi
cd "$ROOT/mmBackend-master"
exec bash ./mvnw "${REPO_ARGS[@]}" spring-boot:run -Dspring-boot.run.profiles=demo
