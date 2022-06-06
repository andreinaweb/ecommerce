package br.com.xyz3.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field;

import java.util.HashMap;

public class EmailService {

    public static void main(String[] args) {
        var emailService = new EmailService();
        try(var service = new KafkaService(EmailService.class.getSimpleName(),
                "ECOMMERCE_SEND_EMAIL",
                emailService::parse,
                String.class,
                new HashMap<>())){ //Pode ser um Map.of() também, como em FraudDetectorService
            service.run();
        }
    }

        private void parse(ConsumerRecord<String,String> record){
            System.out.println("-----------------------------------------------------------");
            System.out.println("Send email");
            System.out.println(record.key());
            System.out.println(record.value());
            System.out.println(record.partition());
            System.out.println(record.offset());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ignoring
                e.printStackTrace();
            }
            System.out.println("Email send!");
        }

}
