FROM eclipse-temurin:17-jdk-alphine AS build
COPY Server.java .
RUN javac Server.java



FROM eclipse-temurin:17-jre-alphine 
WORKDIR /app
COPY --from=build /Server.class .
EXPOSE 8080
CMD ["java","Server"]
