FROM maven:3.9-eclipse-temurin-24 AS build
WORKDIR /app
COPY backend-acenis/pom.xml .
RUN mvn dependency:go-offline
COPY backend-acenis/src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:24-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]