### Profiles

Maven profiles are included for setting up the database.

Spring profiles are also included for the databases. Matching the same name.

| Profile  | Database              |
|----------|-----------------------|
| h2       | H2 in-memory database |
| mysql    | MySQL database        |
| postgres | PostgreSQL database   |

| Profile     | Server                   |
|-------------|--------------------------|
| development | Development settings     |
| production  | Production settings      |

### Installing

The project can be installed by creating the war file and deploying it into a server.

### Running

To run the project locally use the following Maven command:

```
mvn spring-boot:run -P h2,development -Dspring.profiles.active=h2
```

It will be accessible at http://localhost:8080/

### Packaging the WAR

When creating the WAR file the database connection credentials should be read from the environment:

```
mvn package -P production,mysql
```