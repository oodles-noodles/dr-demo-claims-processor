FROM eclipse-temurin:21-jre

WORKDIR /opt/app
COPY target/dr-demo-claims-processor.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
