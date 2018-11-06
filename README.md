# Read from specific file format and write data to hsqldb

[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![Coverage Status](https://coveralls.io/repos/github/codecentric/springboot-sample-app/badge.svg?branch=master)](https://coveralls.io/github/codecentric/springboot-sample-app?branch=master)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

[Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Gradle](https://gradle.org/)

## Clone

```shell
git clone git@github.com:mariuszwoda/read-from-file-save-to-db.git
```

## Running the application locally

There are several ways to run a Spring Boot application on your local machine.

Note: make sure none app are running on 8080 port.
 
1) One way is to execute the `main` method in the `pl.where2play.savejsonusingjpa.SaveJsonUsingJpaApplication` class from your IDE.

2) From command line as follows

```shell
cd read-from-file-save-to-db
```
```
gradle clean build
```
```
java -jar build/libs/read-from-file-save-to-db-0.0.1-SNAPSHOT.jar inputFlePathExample 
```

Make sure that ```inputFilePathExample``` are correct and input file exists in given directory.

Input file example:
```
{"id":"scsmbstgra", "state":"STARTED", "type":"APPLICATION_LOG", "host":"12345", "timestamp":1491377495216}
{"id":"scsmbstgrb", "state":"STARTED", "timestamp":1491377495213}
{"id":"scsmbstgrc", "state":"FINISHED", "timestamp":1491377495218}
{"id":"scsmbstgra", "state":"FINISHED", "type":"APPLICATION_LOG","host":"12345", "timestamp":1491377495217}
{"id":"scsmbstgrc", "state":"STARTED", "timestamp":1491377495210}
{"id":"scsmbstgrb", "state":"FINISHED", "timestamp":1491377495216}
```

## Access

From browser
```
http://localhost:8080/events/list
```

hsqldb settings:
```
spring.datasource.url = jdbc:hsqldb:file:events
spring.datasource.username=sa
```


## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.
