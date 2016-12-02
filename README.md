# Java REPL

[![Build Status](https://travis-ci.org/albertlatacz/java-repl.png?branch=master)](https://travis-ci.org/albertlatacz/java-repl)


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

Web version is available at [www.javarepl.com](http://www.javarepl.com/).


## Build
Building Java REPL requires the [ant build tool](http://ant.apache.org/).

After cloning the git repository, navigate over to it and run:

```
$ ant
```

After this completes, the jar completed with bundled dependencies will be located at **build/artifacts/javarepl-dev.build.jar**

## Releases

Releases are automatically released to Bintray Maven repository

To configure specify repository 

```xml
<repositories>
    <repository>
        <id>Albert Latacz's Maven</id>
        <url>https://dl.bintray.com/albertlatacz/maven</url>
    </repository>
</repositories>
```

and then

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
To run Java REPL you need to install **Java Development Kit (JDK) 6 or newer**. Download it from [here](http://www.oracle.com/technetwork/java/javase/downloads/index.html) and follow install instructions. Once JDK is installed and configured you can run:   


Download latest Java REPL release from [GitHub](https://github.com/albertlatacz/java-repl/releases/latest) or [Bintray](https://bintray.com/albertlatacz/maven/java-repl/_latestVersion) then run


```
$ java -jar javarepl.jar
```

If this doesn't work try to run pointing directly to *java* executable within JDK, like so


```
$ <PATH_TO_JDK>/bin/java -jar javarepl.jar
```


## License

Distributed under the [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)
