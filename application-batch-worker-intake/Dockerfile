FROM openjdk:latest
ENV JAR_NAME=application-batch-worker-intake-0.0.1-SNAPSHOT-boot.jar
ENV APP_HOME=/usr/app/
# https://stackoverflow.com/a/66128131/8524651
RUN rm -rf $APP_HOME
WORKDIR $APP_HOME
COPY . .
RUN ls

RUN chmod +x ./gradlew
RUN ./gradlew :application-batch-worker-intake:bootJar

# Same as spring config
EXPOSE 8082

ENTRYPOINT exec java -jar -Dspring.profiles.active=prod application-batch-worker-intake/build/libs/$JAR_NAME