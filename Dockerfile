FROM openjdk:11.0-jdk-slim

ENV API_SERVER_PORT=8080
ENV MANAGEMENT_SERVER_PORT=8081
ENV JAVA_OPTS=""
EXPOSE 8080 8081
USER 1001
COPY target/phone-app-back-1.0-SNAPSHOT.jar phone-app-back-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75", "-jar", "phone-app-back-1.0-SNAPSHOT.jar"]
