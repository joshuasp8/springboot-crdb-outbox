# Preconfigured Demo Spring Boot App Template

Demo Spring Boot Kotlin JVM App leveraging JPA and Kafka.<br/>
Uses GraalVM plugin to also create native images.

## Stack
- Spring Boot 3
- Kotlin 2
- Java 21 (GraalVM)
- Gradle 8
- Junit 5
- Postgres
- Kafka

## Run
Before running the app, setup the required dependencies with the bootstrap script: 
`./bootstrap.sh`

Then you can run either:
- `./gradlew bootRun`
- `./gradlew nativeRun`

## Configuration

### Postgres

The application uses Postgres as the default database. You can configure the database connection by modifying the `application.yml` file.

### Kafka

The application uses Kafka as the default message broker. You can configure the Kafka connection by modifying the `application.yml` file.

### Bootstrap Script

The `bootstrap.sh` script is a helper script that sets up the necessary environment dependencies using docker-compose. 

Use `./teardown.sh` to shut down the docker-compose dependencies.

### Reflect Config

Some classes in the app (such as `UUID`) require reflective instantiation. This is configured by adding an entry for the class in `src/main/resources/META-INF/native-image/reflect-config.json`