FROM openjdk:17
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 5000 5001 5002 5003
ENTRYPOINT ["java", "-jar", "app.jar"]
