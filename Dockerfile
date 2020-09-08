FROM openjdk:14
ADD target/mediscreen-practioner-1.jar mediscreen-practioner-1.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "mediscreen-practioner-1.jar"]
