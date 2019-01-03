FROM openjdk:8-jre-alpine

# Application .jar
COPY build/libs/employeemanagementservice-*.jar /app/employeemanagementservice.jar

ENTRYPOINT exec java \
    -Xmx256m \
    -Xss256k \
    -jar \
    /app/employeemanagementservice.jar