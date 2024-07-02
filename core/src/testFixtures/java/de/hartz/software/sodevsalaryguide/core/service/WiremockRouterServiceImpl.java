package de.hartz.software.sodevsalaryguide.core.service;

import de.hartz.software.sodevsalaryguide.core.model.helper.Route;
import de.hartz.software.sodevsalaryguide.core.port.service.RouterService;

public class WiremockRouterServiceImpl implements RouterService {

    public static final int SERVER_PORT = 5875;

    @Override
    public Route getUiBackend() {
        return new Route("localhost", SERVER_PORT);
    }

    @Override
    public Route getServiceBackend() {
        return new Route("localhost", SERVER_PORT);
    }

    @Override
    public Route getFrontend() {
        return new Route("localhost", SERVER_PORT);
    }

    @Override
    public Route getBatch() {
        return new Route("localhost", SERVER_PORT);
    }

    @Override
    public Route getDatabase() {
        return new Route("localhost", SERVER_PORT);
    }

    @Override
    public Route getAMQP() {
        return new Route("localhost", SERVER_PORT);
    }
}
