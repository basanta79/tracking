FROM openjdk:17-jdk-alpine
COPY target/tracking-0.0.1-SNAPSHOT.jar tracking-1.0.0.jar
ENV SPRING_DATASOURCE_URL: "jdbc:mysql://172.17.0.1:3309/tracking"
ENV SPRING_RABBITMQ_HOST=172.17.0.1
ENTRYPOINT ["java","-jar","/tracking-1.0.0.jar"]