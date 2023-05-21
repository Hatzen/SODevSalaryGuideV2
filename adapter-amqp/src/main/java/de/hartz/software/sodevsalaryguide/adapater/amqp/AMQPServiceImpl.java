package de.hartz.software.sodevsalaryguide.adapater.amqp;

import de.hartz.software.sodevsalaryguide.core.model.raw.RawDataSetName;
import de.hartz.software.sodevsalaryguide.core.port.exchange.NoMoreDataAvailableException;
import de.hartz.software.sodevsalaryguide.core.port.service.AMQPReceiveService;
import de.hartz.software.sodevsalaryguide.core.port.service.AMQPSendService;
import lombok.val;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AMQPServiceImpl implements AMQPReceiveService, AMQPSendService {

  @Autowired AmqpTemplate amqpTemplate;

  public RawDataSetName getDatasetName() throws NoMoreDataAvailableException {
    val seconds15 = 5000;
    val result = (RawDataSetName) amqpTemplate.receiveAndConvert(seconds15);
    if (result == null) {
      throw new NoMoreDataAvailableException();
    }
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
