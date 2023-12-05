FROM khipu/openjdk17-alpine
EXPOSE 5500
ADD target/Netology_Diplom_moneytransferservice-0.0.1-SNAPSHOT.jar Netology_Diplom_moneytransferservice.jar
ENTRYPOINT ["java", "-jar", "Netology_Diplom_moneytransferservice.jar"]