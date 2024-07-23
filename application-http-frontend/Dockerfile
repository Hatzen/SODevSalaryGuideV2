FROM tomcat:latest
ENV APP_HOME=/usr/app/
# https://stackoverflow.com/a/66128131/8524651
# RUN rm -rf $APP_HOME
WORKDIR $APP_HOME
COPY . .
RUN chmod +x gradlew
# if gradlew is not found check line endings... https://stackoverflow.com/a/71962964/8524651
RUN ./gradlew :application-http-frontend:bootWar

# Same as spring config
EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=prod
# Copy and rename to Root: https://stackoverflow.com/questions/2031537/deploy-war-on-tomcat-without-the-war-name-in-the-url
RUN cp application-http-frontend/build/libs/application-http-frontend-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war