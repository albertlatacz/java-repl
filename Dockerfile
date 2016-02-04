FROM ubuntu:14.04
ARG JAVA_REPL_VERSION=VERSION_NOT_SET

RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y  software-properties-common && \
    add-apt-repository ppa:webupd8team/java -y && \
    apt-get update && \
    echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y oracle-java8-installer && \
    apt-get clean

WORKDIR javarepl

ADD https://s3.amazonaws.com/albertlatacz.published/repo/javarepl/javarepl/$JAVA_REPL_VERSION/javarepl-$JAVA_REPL_VERSION.jar javarepl/javarepl.jar

EXPOSE 8090

CMD ["java", "-cp", "javarepl/javarepl.jar", "javarepl.web.WebConsoleServer",  "--port=8090"]
