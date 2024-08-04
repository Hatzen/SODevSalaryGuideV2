REM @ECHO OFF
REM Build images..

docker-compose build --no-cache

REM Place example.env vars into docker file: https://github.com/kubernetes/kompose/issues/1289
docker compose --env-file .\example.env --profile production config > docker-compose-resolved.yaml
kompose --file docker-compose-resolved.yaml --profile production convert -o k8ns/

docker context use default


REM https://www.baeldung.com/ops/docker-local-images-minikube
REM Will both fail in .bat file as Invoke-Expression is not recognized
REM https://stackoverflow.com/a/42564211/8524651
REM https://stackoverflow.com/a/49149638/8524651
REM leading to closing bat window..: minikube docker-env | Invoke-Expression
REM minikube -p minikube docker-env --shell powershell | Invoke-Expression

REM https://github.com/kubernetes/minikube/issues/16032
REM TODO remove, wont work as image needs force delete, imagePullPolicy is better
REM TODO: Force or stop minikube might be needed.
REM minikube image rm sodevsalary.env.db:rc2
REM minikube image rm sodevsalary.env.http.api:rc2
REM minikube image rm sodevsalary.env.http.config:rc2
REM minikube image rm sodevsalary.env.http.frontend:rc2
REM minikube image rm sodevsalary.env.batch.intake:rc2

REM Try deleting containers to get rid of above caching issue
minikube delete

REM Turn off emojis
SET MINIKUBE_IN_STYLE=0

REM minikube.exe stop
minikube.exe start

minikube image load sodevsalary.env.db:rc2
minikube image load sodevsalary.env.http.api:rc2
minikube image load sodevsalary.env.http.config:rc2
minikube image load sodevsalary.env.http.frontend:rc2
minikube image load sodevsalary.env.batch.intake:rc2

minikube image ls --format table

minikube kubectl -- apply -f k8ns/ --recursive

REM TODO remove, imagePullPolicy is needed
REM Try avoid caching images https://stackoverflow.com/a/51835397/8524651
REM kubectl rollout restart k8ns/

minikube kubectl -- apply -f router/ingress-resource.yaml

minikube kubectl -- apply -f discovery/spring-service-discovery.yaml


REM For windows needs additional stuff https://stackoverflow.com/a/75353664/8524651
REM minikube addons enable ingress
REM must be run in other terminal
start minikube tunnel

REM open Browser
start "" http://demo.sodevsalary.de/

REM Check status of containers to be not ImagePullBackOff
kubectl get pods --output=wide
kubectl describe ingress sodevsalary-ingress

pause