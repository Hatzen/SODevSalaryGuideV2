import com.github.fridujo.rabbitmq.mock.MockConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
class AMQPTestConfiguration {

  // TODO: Move to test fixtures
  @Primary
  @Bean
  ConnectionFactory connectionFactory() {
    return new CachingConnectionFactory(new MockConnectionFactory());
  }
}
