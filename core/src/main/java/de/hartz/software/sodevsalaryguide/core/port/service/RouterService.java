package de.hartz.software.sodevsalaryguide.core.port.service;

import de.hartz.software.sodevsalaryguide.core.model.helper.Route;

public interface RouterService {

    Route getUiBackend();

    Route getServiceBackend();

    Route getFrontend();

    Route getBatch();

    Route getDatabase();

    Route getAMQP();

}
