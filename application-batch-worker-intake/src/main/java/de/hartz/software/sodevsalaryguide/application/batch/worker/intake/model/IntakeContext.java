package de.hartz.software.sodevsalaryguide.application.batch.worker.intake.model;

import de.hartz.software.sodevsalaryguide.core.model.Computation;
import lombok.Data;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.stereotype.Component;

/**
 * Stores computations as it is transient the data does not support restartability out of the box
 * and the fallback must be defined on its
 * own.
 * For small data with restartability see:
 * https://docs.spring.io/spring-batch/docs/current/reference/html/common-patterns.html#passingDataToFutureSteps
 */
@JobScope
@Component
@Data
public class IntakeContext {
  Computation currentComputation;
}
