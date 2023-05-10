package de.hartz.software.sodevsalaryguide.application.batch.worker.intake;

import de.hartz.software.sodevsalaryguide.adapter.amqp.AMQPTestConfiguration;
import de.hartz.software.sodevsalaryguide.adapter.persistence.PersistenceTestConfig;
import org.springframework.context.annotation.Import;

@Import({BatchConfiguration.class, PersistenceTestConfig.class, AMQPTestConfiguration.class})
public class BatchTestConfig {}
