#Build stage

 # FROM openjdk:latest AS BUILD
 # #
 # WORKDIR /usr/app/
 # COPY . .
 # ARG CACHEBUST=1

# Package stage

FROM openjdk:latest
ENV JAR_NAME=application-batch-worker-intake-0.0.1-SNAPSHOT-boot.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
# TODO: This is cached too much, regarding size AND deploying needs a clean..
# COPY --from=BUILD $APP_HOME .
COPY . .
CMD ["./gradlew", ":application-batch-worker-intake:bootJar"]

# Same as spring config
EXPOSE 8082

ENTRYPOINT exec java -jar -Dspring.profiles.active=prod application-batch-worker-intake/build/libs/$JAR_NAME