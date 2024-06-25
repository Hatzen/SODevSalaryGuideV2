FROM openjdk:18

#Build stage

FROM openjdk:latest AS BUILD
WORKDIR /usr/app/
COPY . .

RUN ./gradlew :application-batch-worker-intake:bootJar

# Package stage

FROM openjdk:latest
ENV JAR_NAME=application-batch-worker-intake-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=BUILD $APP_HOME .
# Same as spring config
EXPOSE 8082
ENTRYPOINT exec java -jar $APP_HOME/application-batch-worker-intake/build/libs/$JAR_NAME