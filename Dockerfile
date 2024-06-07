# Use uma imagem base do JDK 17
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR gerado para dentro do container
COPY build/libs/ProspOcean-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta que a aplicação Spring Boot está configurada para escutar
EXPOSE 8080

# Define o comando para executar a aplicação quando o container iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]
