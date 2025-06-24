# --- ESTÁGIO 1: Build (Construção com Java 24) ---
# CORREÇÃO: Usando uma imagem base do Maven que já vem com o JDK 24.
FROM maven:3.9-eclipse-temurin-24 AS build

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia primeiro o pom.xml para aproveitar o cache do Docker
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o resto do código fonte do seu projeto
COPY src ./src

# Executa o comando do Maven para empacotar a aplicação em um .jar
RUN mvn clean package -DskipTests


# --- ESTÁGIO 2: Run (Execução com Java 24) ---
# CORREÇÃO: Usando uma imagem JRE (Java Runtime Environment) do Java 24.
# Ela é mais leve e segura para rodar a aplicação em produção.
FROM eclipse-temurin:24-jre-jammy

# Define o diretório de trabalho
WORKDIR /app

# Copia APENAS o arquivo .jar gerado no estágio de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta em que a aplicação Spring Boot roda
EXPOSE 8080

# O comando que será executado quando o contêiner iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]