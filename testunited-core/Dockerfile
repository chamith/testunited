FROM openjdk:11
WORKDIR /app
ARG JAR_FILE
COPY ./${JAR_FILE} /app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]