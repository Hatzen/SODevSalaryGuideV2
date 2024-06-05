package de.hartz.software.sodevsalaryguide.application.batch.worker.intake.services;

import de.hartz.software.sodevsalaryguide.application.batch.worker.intake.helper.FileHandler;
import de.hartz.software.sodevsalaryguide.application.batch.worker.intake.model.IntakeContext;
import de.hartz.software.sodevsalaryguide.core.model.Computation;
import de.hartz.software.sodevsalaryguide.core.model.raw.RawDataSetName;
import de.hartz.software.sodevsalaryguide.core.model.raw.RawRow;
import de.hartz.software.sodevsalaryguide.core.port.exchange.NoMoreDataAvailableException;
import de.hartz.software.sodevsalaryguide.core.port.repo.ComputationRepo;
import de.hartz.software.sodevsalaryguide.core.port.service.AMQPReceiveService;
import lombok.NonNull;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InputReader implements ItemReader<RawRow>, StepExecutionListener {

  private final Logger logger = LoggerFactory.getLogger(InputReader.class);
  private final RawRow NO_DATA = null;
  private @Autowired AMQPReceiveService amqpReceiveService;
  private @Autowired DataRestClient dataRestClient;
  private @Autowired IntakeContext intakeContext;
  private FileHandler currentFileHandler;
  @Autowired private ComputationRepo computationCrudRepo;

  @Override
  public void beforeStep(@NonNull StepExecution stepExecution) {
    logger.debug("Line Reader initialized.");
  }

  @Override
  public RawRow read() {
    if (!isMoreDataAvailableAndInitilize()) {
      return NO_DATA;
    }

    RawRow line = currentFileHandler.readLine();
    if (line != NO_DATA) {
      line.setComputation(intakeContext.getCurrentComputation());
      logger.debug("Read line: " + line);
    } else {
      currentFileHandler.closeReader();
      currentFileHandler = null;
      return read();
    }

    return line;
  }

  private boolean isMoreDataAvailableAndInitilize() {
    if (!hasCurrentFileHandlerMoreData()) {
      val currentComputation = intakeContext.getCurrentComputation();
      if (currentComputation != null) {
        currentComputation.setEndtime(LocalDateTime.now());
        computationCrudRepo.save(currentComputation);
      }
      if (amqpReceiveService.queueFinished()) {
        return false;
      }
      RawDataSetName datasetName;
      try {
        datasetName = amqpReceiveService.getDatasetName();
        Computation nextComputation =
            Computation.builder()
                .year(datasetName.getYear())
                .starttime(LocalDateTime.now())
                .chunk(datasetName.getChunk())
                .build();
        nextComputation = computationCrudRepo.save(nextComputation);
        intakeContext.setCurrentComputation(nextComputation);
      } catch (NoMoreDataAvailableException e) {
        logger.warn("NoMoreDataAvailableException", e);
        return false;
      }
      currentFileHandler = new FileHandler(datasetName, dataRestClient);
    }
    return true;
  }

  @Override
  public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
    // When there were no data in queue
    if (currentFileHandler != null) {
      currentFileHandler.closeReader();
    }
    logger.debug("Line Reader ended.");
    return ExitStatus.COMPLETED;
  }

  private boolean hasCurrentFileHandlerMoreData() {
    // when filehandler is null there is no more content from file.
    return currentFileHandler != null;
  }
}
