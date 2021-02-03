FROM openjdk:11
EXPOSE 8000
ARG JAR_FILE=build/libs/captchagenerator.jar
ADD ${JAR_FILE} captchagenerator.jar

ARG lombokjar=lombok.jar
ADD https://projectlombok.org/downloads/${lombokjar} lombok.jar


ENTRYPOINT ["java","-jar","/captchagenerator.jar"]
# Make sure only 1 jar file is assembled
#RUN test $(find ./build/libs -type f -name '*.jar' | wc -l) -eq 1
