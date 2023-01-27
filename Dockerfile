FROM openjdk:11
ADD target/aforca-admin-api-docker.jar aforca-admin-api-docker.jar
ENTRYPOINT ["java", "-jar", "aforca-admin-api-docker.jar"]
EXPOSE 8080