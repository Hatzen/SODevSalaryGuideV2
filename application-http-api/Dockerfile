FROM tomcat:latest
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew :application-http-api:bootWar

# Same as spring config
EXPOSE 8081

ENV SPRING_PROFILES_ACTIVE=prod
# Copy and rename to Root: https://stackoverflow.com/questions/2031537/deploy-war-on-tomcat-without-the-war-name-in-the-url
RUN cp application-http-api/build/libs/application-http-api-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
RUN cp application-http-api/src/docker/tomcat-users.xml /usr/local/tomcat/conf/