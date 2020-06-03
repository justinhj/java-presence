FROM adoptopenjdk/openjdk11:debian

WORKDIR /opt
COPY ./target/friends-0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar friends-0.1-SNAPSHOT.jar

