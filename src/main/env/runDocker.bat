@ECHO OFF

REM  https://stackoverflow.com/a/3981086/8524651
SET PROJECT_NAME=%1
IF "%1"=="" (
    SET PROJECT_NAME=so_guide_local
)
REM docker-compose build --no-cache
docker-compose -p %PROJECT_NAME% --env-file .\example.env up -d --force-recreate
pause