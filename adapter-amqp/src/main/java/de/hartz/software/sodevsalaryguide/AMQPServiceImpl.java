package de.hartz.software.sodevsalaryguide;

import de.hartz.software.sodevsalaryguide.model.raw.RawDataSetName;
import de.hartz.software.sodevsalaryguide.port.service.AMQPReceiveService;
import de.hartz.software.sodevsalaryguide.port.service.AMQPSendService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AMQPServiceImpl implements AMQPReceiveService, AMQPSendService {

  @Autowired AmqpTemplate amqpTemplate;

  public RawDataSetName getDatasetName() {
    return (RawDataSetName) amqpTemplate.receiveAndConvert();
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
