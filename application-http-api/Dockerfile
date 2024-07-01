FROM openjdk:latest AS BUILD
WORKDIR /usr/app/
COPY . .

CMD ["./gradlew", ":application-http-api:bootWar"]

# Package stage

FROM tomcat:latest
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
# TODO: This is cached too much, regarding size AND deploying needs a clean..
COPY --from=BUILD $APP_HOME .

# Same as spring config
EXPOSE 8081

ENV SPRING_PROFILES_ACTIVE=prod
COPY application-http-api/build/libs/application-http-api-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/