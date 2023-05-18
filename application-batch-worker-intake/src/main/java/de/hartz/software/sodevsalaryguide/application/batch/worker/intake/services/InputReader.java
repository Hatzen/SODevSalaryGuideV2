package de.hartz.software.sodevsalaryguide.application.batch.worker.intake.services;

import de.hartz.software.sodevsalaryguide.application.batch.worker.intake.helper.FileHandler;
import de.hartz.software.sodevsalaryguide.core.model.raw.RawDataSetName;
import de.hartz.software.sodevsalaryguide.core.model.raw.RawRow;
import de.hartz.software.sodevsalaryguide.core.port.service.AMQPReceiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InputReader implements ItemReader<RawRow>, StepExecutionListener {

  private final Logger logger = LoggerFactory.getLogger(InputReader.class);

  private @Autowired AMQPReceiveService amqpReceiveService;
  private FileHandler currentFileHandler;
  private RawRow NO_DATA = null;

  @Override
  public void beforeStep(StepExecution stepExecution) {
    logger.debug("Line Reader initialized.");
  }

  @Override
  public RawRow read() throws Exception {
    if (amqpReceiveService.queueFinished()) {
      return NO_DATA;
    }
    if (!hasCurrentFileHandlerMoreData()) {
      RawDataSetName datasetName = amqpReceiveService.getDatasetName();
      if (datasetName == null) {
        return new RawRow(datasetName);
      }
      currentFileHandler = new FileHandler(datasetName);
    }

    RawRow line = currentFileHandler.readLine();

    if (line == NO_DATA) {
      return read();
    }
    logger.debug("Read line: " + line.toString());
    return line;
  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    currentFileHandler.closeReader();
    logger.debug("Line Reader ended.");
    return ExitStatus.COMPLETED;
  }

  private boolean hasCurrentFileHandlerMoreData() {
    // when filehandler is null there is no more content from file.
    return currentFileHandler != null;
  }
}
