FROM openjdk:14
ADD build/libs/medi-practioner.jar medi-practioner.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "medi-practioner.jar"]
