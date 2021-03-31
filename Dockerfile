FROM openjdk:8-jdk-alpine
VOLUME /customers-api
ADD target/CustomersAPI-1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]