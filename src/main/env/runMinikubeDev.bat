@ECHO OFF
REM Build images..
REM docker-compose build --no-cache

REM Place example.env vars into docker file: https://github.com/kubernetes/kompose/issues/1289
REM docker compose --env-file .\example.env --profile production config > docker-compose-resolved.yaml
REM kompose --file docker-compose-resolved.yaml --profile production convert -o k8ns/
cd k8ns

REM W0718 13:01:28.304364    1468 main.go:291] Unable to resolve the current Docker CLI context "default": context "default": context not found: open C:\Users\kaiha\.docker\contexts\meta\37a8eec1ce19687d132fe29051dca629d164e2c4958ba141d5f4133a33f0688f\meta.json: Das System kann den angegebenen Pfad nicht finden.
REM docker context use default

REM https://stackoverflow.com/a/42564211/8524651
REM https://stackoverflow.com/a/49149638/8524651
minikube docker-env | Invoke-Expression

REM Turn off emojis
REM SET MINIKUBE_IN_STYLE=0
REM Set custom name for cluster use first parameter so we can run different environments theoretically.
SET PROJECT_NAME=%1
IF "%1"=="" (
    SET PROJECT_NAME=so-guide-minikube
)

REM TODO: using profile leading to weird errors
REM * Verifiziere Kubernetes Komponenten...
REM   - Verwende Image gcr.io/k8s-minikube/storage-provisioner:v5
REM ! Das Aktivieren von 'storage-provisioner lieferte einen Fehler zurück: running callbacks: [sudo KUBECONFIG=/var/lib/minikube/kubeconfig /var/lib/minikube/binaries/v1.30.0/kubectl apply --force -f /etc/kubernetes/addons/storage-provisioner.yaml: Process exited with status 1
REM stdout:
REM
REM stderr:
REM error: error validating "/etc/kubernetes/addons/storage-provisioner.yaml": error validating data: failed to download openapi: Get "https://localhost:8443/openapi/v2?timeout=32s": dial tcp [::1]:8443: connect: connection refused; if you choose to ignore these errors, turn validation off with --validate=false
REM ]
REM minikube.exe start --profile %PROJECT_NAME%
REM minikube --profile %PROJECT_NAME% kubectl -- apply -f . --recursive
minikube.exe start


REM Setup proper routing. https://medium.com/@seanlinsanity/how-to-expose-applications-running-in-kubernetes-cluster-to-public-access-65c2fa959a3b
REM minikube kubectl -- expose deployment application-http-frontend --type=NodePort --port=8080
REM Add the official Ingress Nginx repository.
REM kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/provider/cloud/deploy.yaml
REM Either install kubectl directly or use it via minikube:  minikube kubectl -- get po -A

minikube kubectl -- apply -f . --recursive
REM TODO: or use ansible and terraform? https://stackoverflow.com/a/59502697/8524651

minikube kubectl -- apply -f router/ingress-resource.yaml
kubectl describe ingress sodevsalary-ingress

REM Check status of containers to be not ImagePullBackOff
kubectl get pods --output=wide

pause

REM TODO: Cleanup?
REM minikube kubectl -- delete deployment hello-world