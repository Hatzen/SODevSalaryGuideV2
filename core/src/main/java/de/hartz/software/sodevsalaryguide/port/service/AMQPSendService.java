package de.hartz.software.sodevsalaryguide.port.service;

import de.hartz.software.sodevsalaryguide.model.raw.RawDataSetName;

public interface AMQPSendService {

  public void queueDatasetName(RawDataSetName datasetName);

  public void setQueueFinished();
}
