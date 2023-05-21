package de.hartz.software.sodevsalaryguide.application.batch.worker.intake.services;

import de.hartz.software.sodevsalaryguide.core.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.core.model.raw.RawRow;
import de.hartz.software.sodevsalaryguide.core.port.repo.RawDataRepo;
import de.hartz.software.sodevsalaryguide.core.rawsurveydata.RawRowMapper;
import lombok.NonNull;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputProcessor implements ItemProcessor<RawRow, SurveyEntry>, StepExecutionListener {
  private final SurveyEntry NO_DATA = null;
  @Autowired private RawDataRepo rawDataRepo;
  private Logger logger = LoggerFactory.getLogger(InputProcessor.class);
  private RawRowMapper rawRowMapper;

  @Override
  public void beforeStep(@NonNull StepExecution stepExecution) {
    rawRowMapper = new RawRowMapper();
    logger.debug("Line Processor initialized.");
  }

  @Override
  public SurveyEntry process(@NonNull RawRow line) throws Exception {
    rawDataRepo.insertRawRow(line);
    val result = rawRowMapper.map(line);
    if (!rawRowMapper.isValidEntry(result)) {
      return NO_DATA;
    }
    return result;
  }

  @Override
  public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
    logger.debug("Line Processor ended.");
    return ExitStatus.COMPLETED;
  }
}
