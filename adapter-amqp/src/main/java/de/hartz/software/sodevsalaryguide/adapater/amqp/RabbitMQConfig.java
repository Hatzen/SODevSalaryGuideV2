package de.hartz.software.sodevsalaryguide.adapater.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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

  // @Value("${rabbitmq.queue.name}")
  private String queue = "myRabbitQueue";

  // @Value("${rabbitmq.queue.json.name}")
  private String jsonQueue = "myJsonQueue";

  // @Value("${rabbitmq.exchange.name}")
  private String exchange = "myExchange";

  // @Value("${rabbitmq.routing.key}")
  private String routingKey = "myRoutingKey";

  // @Value("${rabbitmq.routing.json.key}")
  private String routingJsonKey = "myJsonRoutingKey";

  // spring bean for rabbitmq queue
  @Bean
  public Queue queue() {
    return new Queue(queue);
  }

  // spring bean for queue (store json messages)
  @Bean
  public Queue jsonQueue() {
    return new Queue(jsonQueue);
  }

  // spring bean for rabbitmq exchange
  @Bean
  public TopicExchange exchange() {
    return new TopicExchange(exchange);
  }

  // binding between queue and exchange using routing key
  @Bean
  public Binding binding() {
    return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
  }

  // binding between json queue and exchange using routing key
  @Bean
  public Binding jsonBinding() {
    return BindingBuilder.bind(jsonQueue()).to(exchange()).with(routingJsonKey);
  }

  @Bean
  public MessageConverter converter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(converter());
    return rabbitTemplate;
  }

  /*
  TODO: Duplicate..
  @Bean
  public ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    // TODO: configure proper docker params. https://stackoverflow.com/a/54002920/8524651
    connectionFactory.setAddresses("localhost");
    connectionFactory.setUsername("test");
    connectionFactory.setPassword("test");
    return connectionFactory;
  }
   */
}
