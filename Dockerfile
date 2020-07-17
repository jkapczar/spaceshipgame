FROM maven:3.5.0-jdk-8
WORKDIR /apps
COPY . /apps
#CMD mvn exec:java