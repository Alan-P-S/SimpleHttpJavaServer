FROM eclipse-temurin:17-jdk-alpine AS build
COPY Server.java .
RUN javac Server.java



FROM eclipse-temurin:17-jre-alpine 
WORKDIR /app
COPY --from=build /Server.class .
EXPOSE 8080
CMD ["java","Server"]
