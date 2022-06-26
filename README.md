# vs-lab Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Building a docker container
A docker container running the backend and the database can be built using the docker-compose file in this directory.

## Building a docker image for the backend

The dockerfile for the backend was already provided by the quarkus framework, only small changes had to be made 
in order to make it work. I used the Dockerfile.jvm, which builds the backend to run on a normal JVM, but Quarkus
also provides other options like native mode, which is supposed to use less resources and start much faster.

To build a JVM container, run  
```
./gradlew build
```
then
```
docker build -f src/main/docker/Dockerfile.jvm -t niliit/hse-ss22-vs-lab-backend .
```

## Configure exposed port
The port used by the backend can be configured in the docker-compose.yml file.
Just set the QUARKUS_HTTP_PORT environment variable to the desired port and adjust the port mapping
for the docker container accordingly.

## API Documentation

Swagger-UI is reachable under
```
localhost:8080/q/swagger-ui/#/ 
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/vs-lab-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

## Related Guides

- Hibernate ORM ([guide](https://quarkus.io/guides/hibernate-orm)): Define your persistent model with Hibernate ORM and JPA
- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI

## Provided Code

### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)
