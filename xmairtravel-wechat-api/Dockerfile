FROM java:8
ADD xmairtravel-wechat-api-1.0-SNAPSHOT.jar server.jar
ENTRYPOINT ["java","-jar","-Xms512m","-Xmx512m","-Xss256k","-Xmn200m", "-XX:-OmitStackTraceInFastThrow", "-Dfile.encoding=UTF-8","/server.jar"]
EXPOSE 9100