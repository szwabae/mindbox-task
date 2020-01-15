##CNCF Landscape data viewer

This project was created as solution for Mindbox recruiting task.
Assumptions:
* getting and caching data from CNCF Landscape and presenting the result in json, yaml and html formats,
* storing configuration in k8s,
* usage of Drone.io for deploying on 3 environments (dev, stg, prod),
* usage of newest Spring Boot version (2.2.2.RELEASE) and Java 11.

###Spring Boot app
**Gradle** was used for dependency management. For caching purpose **Redis** dependencies were added.
Moreover for handling different files' extensions **Thymeleaf** and **Jackson** were used. **Lombok** helped to avoid writing boilerplate code.

Application can be run locally by using environment variables stored in local.env file. 
There is support for providing file extension in URL but the result can be also obtained by specifying value of Accept header.
By default json is returned.

For json use:
curl --location --request GET 'localhost:8080/landscape.json'
**or**
curl --location --request GET 'localhost:8080/landscape' \
--header 'Accept: application/json'

For yaml use:
curl --location --request GET 'localhost:8080/landscape.x-yaml'
**or**
curl --location --request GET 'localhost:8080/landscape' \
--header 'Accept: application/x-yaml

For html use:
curl --location --request GET 'localhost:8080/landscape.html'
**or**
curl --location --request GET 'localhost:8080/landscape' \
--header 'Accept: text/html'

There is also simple unit test for controller.

###Git
Three separate branches were created. Master should be used for production, dev and stg for corresponding environments.
k8s specific stuff is located in another [repo](https://github.com/szwabae/mindbox-k8s).

###Drone.io
For each branch there is separate .drone.yml file. Steps are the same:
* cloning repo,
* clean and build,
* test,
* pushing image to Dockerhub,
* updating correspondent deployment in repo mentioned before.

Due to lack of k8s server it wasn't possible to add one more step and automate deployment process (see *Further improvements* section).

In *clean & build* step Drone.io specific environment variables are used to create a tag that later is used for versioning pushed Docker image.
For updating deployments shell script was created (can be found under scripts/update-manifest.sh).
Whole deployment process could be monitored on https://cloud.drone.io/. Here were also added secrets DOCKERHUB_TOKEN and GITHUB_TOKEN needed in CI/CD process.
Example flow is shown below:

![Drone.io flow](https://imgur.com/DpvieCs.png)

###k8s
In order to provide deploying mechanism separate mentioned before [repo](https://github.com/szwabae/mindbox-k8s) was created.
It is split into three parts - one part per environment. Each directory holds manifests for Redis and Spring Boot application.
Except of these files there are also shell scripts. In order to deploy latest version of application following steps should be performed:
* running of bootstrap.sh that creates 3 separate namespaces and contexts,
* running of environment specific deploy.sh script that pulls from repo, switches to proper context and deploys Redis and app.

###Further improvements
* creation of k8s server to make the deployment process more automated (probably could be done with usage of Google Cloud; this change requires some fixes in .drone.yml as described [here](http://plugins.drone.io/mactynow/drone-kubernetes/)),
* notification system (e.g. through Slack),
* providing password for Redis (security improvement).