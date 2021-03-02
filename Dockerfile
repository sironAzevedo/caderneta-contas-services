FROM openjdk:11
VOLUME /tmp

ADD ./target/caderneta-contas-services-0.0.1-SNAPSHOT.jar caderneta-contas-services.jar
ADD ./docker-entrypoint.sh /

RUN ["chmod", "+x", "/docker-entrypoint.sh"]
ENTRYPOINT ["/docker-entrypoint.sh"]