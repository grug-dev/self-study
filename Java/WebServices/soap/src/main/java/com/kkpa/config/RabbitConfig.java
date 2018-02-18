package com.kkpa.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
public class RabbitConfig implements RabbitListenerConfigurer{
	
	public final static String QUEUE_NAME = "queue-kkpa";
	
	@Bean
	Queue queue() { return new Queue(QUEUE_NAME, false) ;}
	
	@Bean
    TopicExchange exchange() { return new TopicExchange("spring-boot-exchange");  }
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {  return BindingBuilder.bind(queue).to(exchange).with(QUEUE_NAME);   }
	
	@Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }
	
	@Bean
    MessageListenerAdapter listenerAdapter(ReceiverListener receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
		// TODO Auto-generated method stub
		
	}

}
