FROM java:8

EXPOSE 65111

COPY target/distsys-1.0-SNAPSHOT.jar /usr/src/myapp/server.jar

WORKDIR /usr/src/myapp

CMD ["java", "-jar", "server.jar"]

