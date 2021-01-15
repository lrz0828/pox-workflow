FROM harbor_registry/dev/hsf-centos-java:7-1.8
#10.105.29.21:5000/dev/hsf-centos-java:7-1.8
ENV JAVA_HOME /usr/java/latest
ENV CATALINA_HOME /app/admin
ENV PATH ${JAVA_HOME}/bin:${PATH}
#ENV ADMIN_HOME /home/admin
ENV ADMIN_HOME /app/admin

#本地测试
#ENV ENV_FILE=local
#ENV APP_NAME=zipkin-server
#ENV APP_VERSION=0.0.1

#jenkins
ENV ENV_FILE=env_file
ENV APP_NAME=app_name
ENV APP_VERSION=app_version

#表示本docker,要把本地10008进行映射
EXPOSE 9411

COPY target/${APP_NAME}-${APP_VERSION}-SNAPSHOT-assembly.tar.gz ${ADMIN_HOME}/app.tar.gz
WORKDIR /app/admin
RUN tar -xvf ${ADMIN_HOME}/app.tar.gz
RUN rm -rf ${ADMIN_HOME}/app.tar.gz

#cloud
RUN echo '${JAVA_HOME}/bin/java -Dloader.path=${ADMIN_HOME}/${APP_NAME}-${APP_VERSION}-SNAPSHOT/lib -Djava.security.egd=file:/dev/./urandom -jar ${ADMIN_HOME}/${APP_NAME}-${APP_VERSION}-SNAPSHOT/lib/${APP_NAME}-${APP_VERSION}-SNAPSHOT.jar --spring.config.additional-location=classpath:props/${ENV_FILE}/def.properties --server.tomcat.uri-encoding=ISO-8859-1 --server.tomcat.max-threads=400' > ${ADMIN_HOME}/start.sh && chmod +x ${ADMIN_HOME}/start.sh
RUN mkdir -p ${ADMIN_HOME}/logs
CMD /bin/bash ${ADMIN_HOME}/start.sh

##手动编译 docker login -u admin -p Harbor12345 10.68.27.22:5000
#docker build -t zipkin .
#docker run -d -p 9411:9411 --name=zipkin -v /Users/xuyongming/kl/docker/zipkin:/home/admin zipkin