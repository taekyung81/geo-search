FROM adoptopenjdk/openjdk11
VOLUME /tmp
EXPOSE 8080

COPY gs-public-api/build/libs/gs.jar /usr/local/bin/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=$RUN_ENV", "-jar", "/usr/local/bin/app.jar"]
