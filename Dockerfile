FROM eclipse-temurin:21

WORKDIR /app

COPY build/libs/cmarket-1.0.jar app.jar

COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh
   
EXPOSE 8090

# docker run -e SPRING_PROFILES_ACTIVE=prod myapp-image
ENTRYPOINT ["./wait-for-it.sh", "mysql:3306", "--", "java", "-jar", "app.jar"]
