#!/bin/sh
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
APP_NAME=base-facade-0.0.1-SNAPSHOT
APP_DIR="${DEPLOY_DIR}/lib"
rm -f tpid

nohup java -Dloader.path=$DEPLOY_DIR/lib -jar $APP_DIR/$APP_NAME.jar  --spring.config.additional-location=classpath:props/$1/base-application.properties  > $BIN_DIR/run.log  2>&1 &

echo $! > tpid

echo Start Success!
