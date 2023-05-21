package de.hartz.software.sodevsalaryguide.core.port.service;

import de.hartz.software.sodevsalaryguide.core.model.raw.RawDataSetName;
import de.hartz.software.sodevsalaryguide.core.port.exchange.NoMoreDataAvailableException;

public interface AMQPReceiveService {
  public RawDataSetName getDatasetName() throws NoMoreDataAvailableException;

  public boolean queueFinished();
}
