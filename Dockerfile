FROM maven:3.6.3-jdk-11-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -B -f pom.xml clean package -DskipTests

FROM openjdk:11-jdk-slim
COPY --from=build /workspace/target/*.jar caderneta-contas-services.jar
EXPOSE 8001

RUN ["chmod", "+x", "/docker-entrypoint.sh"]
ENTRYPOINT ["/docker-entrypoint.sh"]


#FROM openjdk:11
#VOLUME /tmp
#
#ADD ./target/caderneta-contas-services-0.0.1-SNAPSHOT.jar caderneta-contas-services.jar
#ADD ./docker-entrypoint.sh /
#
#RUN ["chmod", "+x", "/docker-entrypoint.sh"]
#ENTRYPOINT ["/docker-entrypoint.sh"]