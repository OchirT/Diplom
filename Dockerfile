FROM openjdk:17-jdk-alpine
ADD target/DiplomProject-0.0.1-SNAPSHOT.jar diplomproject.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","diplomproject.jar"]
