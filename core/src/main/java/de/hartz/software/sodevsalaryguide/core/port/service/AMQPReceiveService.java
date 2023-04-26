package de.hartz.software.sodevsalaryguide.core.port.service;

import de.hartz.software.sodevsalaryguide.core.model.raw.RawDataSetName;

public interface AMQPReceiveService {
  public RawDataSetName getDatasetName();

  public boolean queueFinished();
}
