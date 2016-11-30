FROM albertlatacz/ubuntu-java-base

ARG JAVA_REPL_VERSION=VERSION_NOT_SET

WORKDIR javarepl

ADD https://github.com/albertlatacz/java-repl/releases/download/$JAVA_REPL_VERSION/javarepl-$JAVA_REPL_VERSION.jar javarepl/javarepl.jar

EXPOSE 8090

CMD ["java", "-cp", "javarepl/javarepl.jar", "javarepl.web.WebConsoleServer",  "--port=8090"]
