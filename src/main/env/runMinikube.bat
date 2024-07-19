REM @ECHO OFF
REM Build images..
REM docker-compose build --no-cache

REM Place example.env vars into docker file: https://github.com/kubernetes/kompose/issues/1289
REM docker compose --env-file .\example.env --profile production config > docker-compose-resolved.yaml
REM kompose --file docker-compose-resolved.yaml --profile production convert -o k8ns/

REM docker context use default


REM https://www.baeldung.com/ops/docker-local-images-minikube
REM Will both fail in .bat file as Invoke-Expression is not recognized
REM https://stackoverflow.com/a/42564211/8524651
REM https://stackoverflow.com/a/49149638/8524651
REM leading to closing bat window..: minikube docker-env | Invoke-Expression
REM minikube -p minikube docker-env --shell powershell | Invoke-Expression


minikube image load sodevsalary.env.db:rc2
minikube image load sodevsalary.env.http.api:rc2
minikube image load sodevsalary.env.http.frontend:rc2
minikube image load sodevsalary.env.batch.intake:rc2

REM minikube image ls --format table

REM Turn off emojis
SET MINIKUBE_IN_STYLE=0

minikube.exe stop
minikube.exe start

minikube kubectl -- apply -f k8ns/ --recursive

minikube kubectl -- apply -f router/ingress-resource.yaml
kubectl describe ingress sodevsalary-ingress

REM Check status of containers to be not ImagePullBackOff
kubectl get pods --output=wide

pause