FROM openjdk:17 as build

WORKDIR /moro-client

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# Fetch all dependencies
RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw package -DskipTests

FROM openjdk:17

COPY --from=build /moro-client/target/*.jar moro-client.jar

ENTRYPOINT ["java","-jar","/moro-client.jar"]

