FROM amazoncorretto:21 as builder
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw package -DskipTests

FROM amazoncorretto:21 as prod

WORKDIR /app

COPY --from=builder /app/target/*.jar ./app.jar

CMD ["java", "-jar", "app.jar"]