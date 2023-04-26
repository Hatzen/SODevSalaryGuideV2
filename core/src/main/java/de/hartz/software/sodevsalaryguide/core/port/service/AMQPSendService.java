package de.hartz.software.sodevsalaryguide.core.port.service;

import de.hartz.software.sodevsalaryguide.core.model.raw.RawDataSetName;

public interface AMQPSendService {

  public void queueDatasetName(RawDataSetName datasetName);

  public void setQueueFinished();
}
