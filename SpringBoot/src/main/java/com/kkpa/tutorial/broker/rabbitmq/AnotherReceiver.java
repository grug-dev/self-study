package com.kkpa.tutorial.broker.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.kkpa.tutorial.config.RabbitMQConfig;

//@Component
//@RabbitListener(queues = RabbitMQConfig.ANOTHER_QUEUE)
public class AnotherReceiver {


  @RabbitHandler
  public void receive(String message) {
    System.out.println("Im another receiver of " + message);
  }

}
