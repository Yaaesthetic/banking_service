FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN apk add --no-cache maven && \
    mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /build/target/banking_service-1.0-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-cp", "app.jar"]

# Default main class (can be overridden)
CMD ["ma.demo.Main"]