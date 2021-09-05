FROM adoptopenjdk:11-jre-hotspot
ADD target/*.jar application.jar
ENTRYPOINT ["java", "-jar","application.jar"]
EXPOSE 8080