FROM amazoncorretto:11-alpine-jdk
MAINTAINER Lucrecia
COPY target/portfolio-0.0.1-SNAPSHOT portfolio.jar
ENTRYPOINT ["java","-jar","/portfolio.jar"]
