FROM openjdk:17 as build

WORKDIR /moro-server

COPY mvnw .
COPY .mvn .mvn

COPY pom.xml .

RUN ./mvnw dependency:go-offline -B

COPY src src
COPY db db

RUN ./mvnw package -DskipTests

FROM openjdk:17

COPY --from=build /moro-server/target/*.jar moro-server.jar

ENTRYPOINT ["java","-jar","/moro-server.jar"]

