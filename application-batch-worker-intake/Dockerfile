#Build stage

FROM openjdk:latest AS BUILD
#
WORKDIR /usr/app/
COPY . .

CMD ["./gradlew", ":application-batch-worker-intake:bootJar"]

# Package stage

FROM openjdk:latest
ENV JAR_NAME=application-batch-worker-intake-0.0.1-SNAPSHOT-boot.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=BUILD $APP_HOME .
# Same as spring config
EXPOSE 8082

# TODO: Fix prod config with docker ports..
# 2024-06-25 17:29:15 org.postgresql.util.PSQLException: Connection to localhost:9999 refused. Check that the hostname and port are correct and that the postmaster is accepting TCP/IP connections.
ENTRYPOINT exec java -jar -Dspring.profiles.active=prod $APP_HOME/application-batch-worker-intake/build/libs/$JAR_NAME