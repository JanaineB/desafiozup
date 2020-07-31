FROM maven:3.6.3-adoptopenjdk-11-openj9 AS builder
WORKDIR app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn -e -B package -DskipTests

FROM adoptopenjdk/openjdk11-openj9:alpine-jre
COPY --from=builder /app/target/app.jar /
CMD ["java", "-jar", "app.jar"]