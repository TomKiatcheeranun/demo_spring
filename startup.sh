#!/bin/bash
export LANG="en_US.UTF-8"
APP_NAME=$(cat /image_info/app_name)
APP_VERSION=$(cat /image_info/app_version)

echo "Start Application"
chmod +x /project/$APP_NAME-$APP_VERSION.jar

java -XX:MaxMetaspaceSize=128m -Xss1m -jar /project/$APP_NAME-$APP_VERSION.jar
