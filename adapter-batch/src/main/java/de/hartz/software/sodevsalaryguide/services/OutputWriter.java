package de.hartz.software.sodevsalaryguide.services;

import de.hartz.software.sodevsalaryguide.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.port.repo.EvaluatedDataWriteRepo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OutputWriter implements ItemWriter<SurveyEntry>, StepExecutionListener {

  private final EvaluatedDataWriteRepo evaluatedDataWriteRepo;
  private final Logger logger = LoggerFactory.getLogger(OutputWriter.class);

  @Override
  public void beforeStep(StepExecution stepExecution) {
    logger.debug("Line Writer initialized.");
  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    logger.debug("Line Writer ended.");
    return ExitStatus.COMPLETED;
  }

  @Override
  public void write(Chunk<? extends SurveyEntry> chunk) throws Exception {
    evaluatedDataWriteRepo.insertAllSurveyEntries((List<SurveyEntry>) chunk.getItems());
    logger.debug("Chunk persisted");
  }
}
