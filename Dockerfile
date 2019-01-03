FROM openjdk:8-jre-alpine

# Application .jar
COPY build/libs/employeemanagementservice-0.0.1*.jar /app/employeemanagementservice-0.0.1.jar

ENTRYPOINT exec java \
    -Xmx256m \
    -Xss256k \
    -jar \
    /app/employeemanagementservice-0.0.1.jar