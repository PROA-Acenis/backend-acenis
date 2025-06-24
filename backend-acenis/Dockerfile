# --- ESTÁGIO 1: Build (Construção com Java 24) ---
# Esta parte está correta e não precisa de alteração
FROM maven:3.9-eclipse-temurin-24 AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests


# --- ESTÁGIO 2: Run (Execução com Java 24) ---
# CORREÇÃO: Trocamos '24-jre-jammy' por '24-jre', que é uma tag válida.
FROM eclipse-temurin:24-jre

WORKDIR /app

# Copia APENAS o arquivo .jar gerado no estágio de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta em que a aplicação Spring Boot roda
EXPOSE 8080

# O comando que será executado quando o contêiner iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]