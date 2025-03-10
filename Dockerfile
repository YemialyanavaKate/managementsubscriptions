FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/managementsubscriptions-0.0.1-SNAPSHOT.jar /app/managementsubscriptions-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "managementsubscriptions-0.0.1-SNAPSHOT.jar"]