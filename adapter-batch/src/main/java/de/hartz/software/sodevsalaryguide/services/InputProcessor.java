package de.hartz.software.sodevsalaryguide.services;

import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.RawRowMapper;
import de.hartz.software.sodevsalaryguide.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.model.raw.RawRow;
import de.hartz.software.sodevsalaryguide.port.repo.RawDataRepo;
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
  public void beforeStep(StepExecution stepExecution) {
    rawRowMapper = new RawRowMapper();
    logger.debug("Line Processor initialized.");
  }

  @Override
  public SurveyEntry process(RawRow line) throws Exception {
    // TODO: Persist Chunks...
    rawDataRepo.insertRawRow(line);
    val result = rawRowMapper.map(line);
    if (!rawRowMapper.isValidEntry(result)) {
      return NO_DATA;
    }
    return result;
  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    logger.debug("Line Processor ended.");
    return ExitStatus.COMPLETED;
  }
}
