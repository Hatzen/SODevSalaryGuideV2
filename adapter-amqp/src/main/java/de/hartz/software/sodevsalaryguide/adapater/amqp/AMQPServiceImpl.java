package de.hartz.software.sodevsalaryguide.adapater.amqp;

import de.hartz.software.sodevsalaryguide.core.model.raw.RawDataSetName;
import de.hartz.software.sodevsalaryguide.core.port.exchange.NoMoreDataAvailableException;
import de.hartz.software.sodevsalaryguide.core.port.service.AMQPReceiveService;
import de.hartz.software.sodevsalaryguide.core.port.service.AMQPSendService;
import lombok.val;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AMQPServiceImpl implements AMQPReceiveService, AMQPSendService {

  @Autowired AmqpTemplate amqpTemplate;

  private HashSet<RawDataSetName> processedNames = new HashSet<>();

  public RawDataSetName getDatasetName() throws NoMoreDataAvailableException {
    val seconds15 = 15000;
    val result = (RawDataSetName) amqpTemplate.receiveAndConvert(seconds15);
    if (result == null) {
      throw new NoMoreDataAvailableException();
    }
    // TODO: REmove, just dummy handler for erroneous mocking lib or setup..
    if (processedNames.contains(result)) {
      throw new NoMoreDataAvailableException();
    }
    processedNames.add(result);
    return result;
  }

  public boolean queueFinished() {
    // TODO: Read queue and do not acknowledge
    return false;
  }

  @Override
  public void queueDatasetName(RawDataSetName datasetName) {
    amqpTemplate.convertAndSend(datasetName);
  }

  @Override
  public void setQueueFinished() {
    // TODO: Use queue and pass entry
  }
}
