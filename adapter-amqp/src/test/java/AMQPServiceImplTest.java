import de.hartz.software.sodevsalaryguide.adapater.amqp.RabbitMQConfig;
import de.hartz.software.sodevsalaryguide.adapter.amqp.AMQPTestConfiguration;
import de.hartz.software.sodevsalaryguide.core.model.raw.RawDataSetName;
import de.hartz.software.sodevsalaryguide.core.port.exchange.NoMoreDataAvailableException;
import de.hartz.software.sodevsalaryguide.core.port.service.AMQPReceiveService;
import de.hartz.software.sodevsalaryguide.core.port.service.AMQPSendService;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RabbitMQConfig.class, AMQPTestConfiguration.class})
@ActiveProfiles({"amqp-test"})
public class AMQPServiceImplTest {

  @Autowired AMQPSendService sendService;
  @Autowired AMQPReceiveService receiveService;

  @Test
  public void writeAndRead_receivesExactSameData_processesAllData()
      throws NoMoreDataAvailableException {
    val testName1 = new RawDataSetName("2011-chunk-1", 2011);
    sendService.queueDatasetName(testName1);

    val result = receiveService.getDatasetName();

    Assertions.assertEquals(result, testName1);
  }
}
