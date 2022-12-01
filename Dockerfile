FROM openjdk:8-jdk-alpine
MAINTAINER pattimuradev
COPY ./target/ChallengeChapter6BackEndJava-0.0.1-SNAPSHOT.jar ChallengeChapter6BackEndJava.jar
CMD ["java","-jar","ChallengeChapter6BackEndJava.jar"]