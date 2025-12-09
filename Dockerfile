# Build
FROM eclipse-temurin:25-jdk AS build
WORKDIR /workspace

COPY gradlew settings.gradle build.gradle ./
COPY gradle gradle
RUN chmod +x gradlew

COPY src src

RUN ./gradlew clean bootJar -x test

# Run
FROM eclipse-temurin:25-jdk
WORKDIR /app

COPY --from=build /workspace/build/libs/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
