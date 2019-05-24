package springboot.springbootkafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

@Component
public class KafkaProducer {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Scheduled(cron = "0/2 * * * * ?")
    public void send(){
        String message = UUID.randomUUID().toString();
        ListenableFuture listenableFuture = kafkaTemplate.send("test", message);
        listenableFuture.addCallback(o -> System.out.println("send 消息发送成功 " + message), throwable -> {
            System.out.println("send 消息发送失败 " + message);
        } );


    }
}
