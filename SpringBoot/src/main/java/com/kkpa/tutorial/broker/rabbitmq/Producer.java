package com.kkpa.tutorial.broker.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import com.kkpa.tutorial.config.RabbitMQConfig;

@Component
public class Producer {


  private RabbitTemplate rabbitTemplate;

  public Producer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }


  public void sendMessage(String message) {
    rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, message);
    rabbitTemplate.convertAndSend(RabbitMQConfig.ANOTHER_QUEUE, message);
  }

}
