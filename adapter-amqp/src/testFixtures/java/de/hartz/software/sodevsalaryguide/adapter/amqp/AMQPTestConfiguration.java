package de.hartz.software.sodevsalaryguide.adapter.amqp;

import com.github.fridujo.rabbitmq.mock.compatibility.MockConnectionFactoryFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Primary
@Configuration
public class AMQPTestConfiguration {

  @Bean
  public ConnectionFactory connectionFactory() {
    return new CachingConnectionFactory(
        MockConnectionFactoryFactory.build().enableConsistentHashPlugin()
        // .withAdditionalExchange(
        //     MockExchangeCreator.creatorWithExchangeType(
        //         MockDefaultExchange.TYPE, new MockDefaultExchange(new MockNode())))
        );
    // .withAdditionalExchange(
    //    creatorWithExchangeType("x-fix-delayed-message", FixDelayExchange::new)));
  }

  @Bean
  public RabbitAdmin rabbitAdmin(
          ConnectionFactory connectionFactory, Queue queue, Exchange exchange, Binding binding) {
    RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
    rabbitAdmin.declareQueue(queue);
    rabbitAdmin.declareExchange(exchange);
    rabbitAdmin.declareBinding(binding);
    return rabbitAdmin;
  }

}
