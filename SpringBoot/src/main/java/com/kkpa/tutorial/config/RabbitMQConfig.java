package com.kkpa.tutorial.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.kkpa.tutorial.broker.rabbitmq.AnotherReceiver;
import com.kkpa.tutorial.broker.rabbitmq.Receiver;

//@Configuration
public class RabbitMQConfig {

  public final static String QUEUE_NAME = "kkpa-queue";
  public final static String ANOTHER_QUEUE = "another-kkpa-queue";

  public static final String topicExchangeName = "kkpa-exchange";

  public static final String EXCHANGE_ROUTING_KEY = "foo.bar.";


  //@Bean
  Queue queue() {
    return new Queue(QUEUE_NAME, false);
  }

  //@Bean
  Queue queue2() {
    return new Queue(ANOTHER_QUEUE, false);
  }

  //@Bean
  TopicExchange exchange() {
    return new TopicExchange(topicExchangeName);
  }

  //@Bean
  Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(EXCHANGE_ROUTING_KEY + ".#");
  }

  //@Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
      MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueues(queue());
    container.setMessageListener(listenerAdapter);
    return container;
  }

  //@Bean
  SimpleMessageListenerContainer container2(ConnectionFactory connectionFactory,
      MessageListenerAdapter listenerAdapter2) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueues(queue2());
    container.setMessageListener(listenerAdapter2);
    return container;
  }

  //@Bean
  MessageListenerAdapter listenerAdapter(Receiver receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }

  //@Bean
  MessageListenerAdapter listenerAdapter2(AnotherReceiver receiver) {
    return new MessageListenerAdapter(receiver, "receive");
  }


}
