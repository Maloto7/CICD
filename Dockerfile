FROM maven:latest
ADD target/devops-test.jar devops-test.jar
ENTRYPOINT ["java","-jar","/devops-test.jar"]