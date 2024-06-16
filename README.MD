Checkout repo and start src/main/env/init.sh for linux or .bat for windows with preinstalled docker desktop

setup code
- the gradlew is currently targeting java 17
- add lombok plugin if constructors are not recognized
- application modules will create a spring boot jar
- main project build will fail

Currently there is an issue with the batch component test
- https://github.com/fridujo/rabbitmq-mock/issues/303

# Architecture
- Microservice / Makroservice / Multimodule monolith
- Currently working on 1 Database
- Creating 2 Containers: Webui and Batch
  - Frontend could be custom nginx container for load balancing
  - Webui could be seperated to a service container for managing files

# Potential features
- Load data from database; load all valid enums from backend
- One Click/ Command setup running e2e test which creates initial data
- Use docker compose for local startup
  - start worker batches and scale when needed
- Convert docker compose to helm charts
  - use terraform to deploy on aws, azure, google cloud etc

Optional
- Use GraphQL to browse rawdata (only loaded in new tab)
- Use Webflux to check if batches are still running
- Use zookeeper for routing
- Use Spring Security to add new files
- Use Swagger for documentation
- Rolling updates / CI CD
- Setup CI CD with Snyk and Coverage etc.
- add currency conversion service 
- secure connections / Https and certs
- Terraform / Helmcharts
- Scale Worker Batches
