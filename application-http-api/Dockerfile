FROM openjdk:latest AS BUILD
WORKDIR /usr/app/
COPY . .

CMD ["./gradlew", ":application-http-api:bootWar"]
# RUN ./gradlew :application-http-api:bootWar

# Package stage

FROM tomcat:latest
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=BUILD $APP_HOME .

# TODO: Fix prod config with docker ports..
# 2024-06-25 17:29:15 org.postgresql.util.PSQLException: Connection to localhost:9999 refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.
ENV SPRING_PROFILES_ACTIVE=prod
COPY application-http-api/build/libs/application-http-api-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/