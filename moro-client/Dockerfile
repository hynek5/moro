FROM openjdk:17 as build

WORKDIR /moro-client

COPY mvnw .
COPY .mvn .mvn

COPY pom.xml .

RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw package -DskipTests

FROM openjdk:17

COPY --from=build /moro-client/target/*.jar moro-client.jar

ENTRYPOINT ["java","-jar","/moro-client.jar"]

