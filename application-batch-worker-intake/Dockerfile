FROM openjdk:latest
ENV JAR_NAME=application-batch-worker-intake-0.0.1-SNAPSHOT-boot.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY . .

# https://stackoverflow.com/questions/73516116/got-error-xargs-is-not-available-when-trying-to-run-a-docker-image
RUN microdnf install findutils


RUN chmod +x ./gradlew
RUN ./gradlew :application-batch-worker-intake:bootJar

# Same as spring config
EXPOSE 8082

ENTRYPOINT exec java -jar -Dspring.profiles.active=prod application-batch-worker-intake/build/libs/$JAR_NAME