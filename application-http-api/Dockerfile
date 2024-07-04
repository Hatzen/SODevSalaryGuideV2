# FROM openjdk:latest AS BUILD
# WORKDIR /usr/app/
# COPY . .
#
# CMD ["./gradlew", ":application-http-api:bootWar"]

# Package stage

FROM tomcat:latest
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
# TODO: This is cached too much, regarding size AND deploying needs a clean..
# COPY --from=BUILD $APP_HOME .
COPY . .

CMD ["./gradlew", ":application-http-api:bootWar"]

# Same as spring config
EXPOSE 8081

ENV SPRING_PROFILES_ACTIVE=prod
# Copy and rename to Root: https://stackoverflow.com/questions/2031537/deploy-war-on-tomcat-without-the-war-name-in-the-url
COPY application-http-api/build/libs/application-http-api-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war