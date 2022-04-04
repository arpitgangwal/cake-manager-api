FROM openjdk:8
EXPOSE 8080
ADD target/cake-manager-api.jar cake-manager-api.jar
ENTRYPOINT ["sh", "-c","java $JAVA_OPTS -Djasypt.encryptor.password=test -jar /cake-manager-api.jar"]