# Java REPL

### NOT MAINTAINED: Since Java is now released with [REPL](https://docs.oracle.com/javase/10/jshell/introduction-jshell.htm) this project will no longer be maintained.

[![Travis](https://img.shields.io/travis/albertlatacz/java-repl.svg)](https://travis-ci.org/albertlatacz/java-repl)
[![GitHub](https://img.shields.io/github/release/albertlatacz/java-repl.svg)](https://github.com/albertlatacz/java-repl/releases/latest)
[![Docker Pulls](https://img.shields.io/docker/pulls/albertlatacz/java-repl.svg)](https://hub.docker.com/r/albertlatacz/java-repl/)


 


Java REPL is a simple Read-Eval-Print-Loop for Java language.

* Support for most of Java language constructs
* Access from console as well as web terminal via browser
* Create imports, methods, classes, enums and interfaces
* Load classes from any jar file or directory (including web urls)
* Load and evaluate expression from file
* List previous results, imports, created types and methods
* Show history of evaluations
* Search and evaluate expression from history
* Clear and replay previous evaluations
* Load and evaluate any Java source file
* Basic code completion for results, classes and methods
* Coloured output for console and web terminal



## Build
Building Java REPL requires the [gradle](https://gradle.org/).

After cloning the git repository, navigate over to it and run:

```
$ gradle shadowJar
```

After this completes, the jar completed with bundled dependencies will be located at **build/libs/javarepl-dev.jar**

Type the following to run

```
$ java -jar build/libs/javarepl-dev.jar
```

## Releases

Releases are automatically released to [![Maven Central](https://img.shields.io/maven-central/v/com.javarepl/javarepl.svg)](http://repo1.maven.org/maven2). Configure repository and then add Java REPL as dependency:

```xml
<dependencies>
    <dependency>
        <groupId>com.javarepl</groupId>
        <artifactId>javarepl</artifactId>
        <version>SOME_VERSION</version>
    </dependency>
</dependencies>
```

## Usage
To run Java REPL you need to install **Java Development Kit (JDK)**. Download it from [here](http://www.oracle.com/technetwork/java/javase/downloads/index.html) and follow install instructions. Once JDK is installed and configured, download latest Java REPL release from [GitHub](https://github.com/albertlatacz/java-repl/releases/latest) or [Bintray](https://bintray.com/albertlatacz/maven/java-repl/_latestVersion) then run:


```
$ java -jar javarepl-SOME_VERSION.jar
```

If this doesn't work try to run pointing directly to *java* executable within JDK, like so


```
$ <PATH_TO_JDK>/bin/java -jar javarepl-SOME_VERSION.jar
```


## License

Distributed under the [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)
