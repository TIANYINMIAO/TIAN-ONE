FROM swr.cn-north-4.myhuaweicloud.com/ddn-k8s/docker.io/openjdk:17-jdk-alpine
ADD huanghe-0.0.1-SNAPSHOT.jar huanghe.jar
ENTRYPOINT ["java","-jar","/huanghe.jar"]