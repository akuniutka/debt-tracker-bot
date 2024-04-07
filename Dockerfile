FROM eclipse-temurin:11-jdk as build
RUN apt-get update
RUN apt-get upgrade -y
RUN apt-get install -y git
RUN git clone https://github.com/akuniutka/debt-tracker-bot.git
RUN apt-get install -y maven
WORKDIR debt-tracker-bot
RUN mvn clean package
FROM eclipse-temurin:11-jre as target
COPY --from=build /debt-tracker-bot/target/debt-tracker-bot-1.0.0-SNAPSHOT.jar /app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app.jar"]