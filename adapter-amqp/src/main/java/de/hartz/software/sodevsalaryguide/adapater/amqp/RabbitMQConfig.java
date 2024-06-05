package de.hartz.software.sodevsalaryguide.adapater.amqp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class RabbitMQConfig {
  public static final String jsonQueue = "intakeFileNameQueueName";
  public static final String exchange = "myExchange";
  public static final String routingKey = "myRoutingKey";
  public static final String routingJsonKey = "myJsonRoutingKey";

  // spring bean for queue (store json messages)
  @Bean
  public Queue jsonQueue() {
    return new Queue(jsonQueue, true, false, false);
  }

  // spring bean for rabbitmq exchange
  @Bean
  public TopicExchange exchange() {
    return new TopicExchange(exchange);
  }

  // binding between queue and exchange using routing key
  @Bean
  public Binding binding(Queue jsonQueue, TopicExchange exchange) {
    return BindingBuilder.bind(jsonQueue).to(exchange).with(routingKey);
  }

  // binding between json queue and exchange using routing key
  @Bean
  public Binding jsonBinding(Queue jsonQueue, TopicExchange exchange) {
    return BindingBuilder.bind(jsonQueue).to(exchange).with(routingJsonKey);
  }

  @Bean
  public MessageConverter converter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory, MessageConverter converter) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setDefaultReceiveQueue(jsonQueue);
    rabbitTemplate.setRoutingKey(routingJsonKey);
    rabbitTemplate.setExchange(exchange);
    rabbitTemplate.setMessageConverter(converter);

    rabbitTemplate.setMandatory(true);
    rabbitTemplate.setConfirmCallback(
        (correlationData, ack, cause) -> {
          System.err.println("test");
        });
    // rabbitTemplate.containerAckMode(AcknowledgeMode.AUTO);
    rabbitTemplate.containerAckMode(AcknowledgeMode.AUTO);
    return rabbitTemplate;
  }

  @Bean
  public ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    // TODO: configure proper docker params. https://stackoverflow.com/a/54002920/8524651
    connectionFactory.setAddresses("localhost");
    connectionFactory.setPort(5672);
    connectionFactory.setUsername("admin");
    connectionFactory.setPassword("password123");
    return connectionFactory;
  }

}
