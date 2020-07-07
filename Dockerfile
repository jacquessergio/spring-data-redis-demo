FROM openjdk:8
ADD target/RedisDemo.jar RedisDemo.jar
EXPOSE 8085
ENTRYPOINT ["sh", "-c", "java -jar /RedisDemo.jar"]	