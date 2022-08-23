
FROM adoptopenjdk/openjdk11:alpine-jre
#VOLUME /tmp
#EXPOSE 8081
#ADD target/spring-boot-docker.jar spring-boot-docker.jar
#ENTRYPOINT ["java","-jar","/spring-boot-docker.jar"]

VOLUME /tmp
EXPOSE 8081
ADD target/*.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]


