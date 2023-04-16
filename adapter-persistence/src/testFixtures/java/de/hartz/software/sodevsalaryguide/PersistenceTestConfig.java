package de.hartz.software.sodevsalaryguide;

import org.springframework.context.annotation.Import;

@Import(value = PersistenceConfiguration.class)
public class PersistenceTestConfig {}
