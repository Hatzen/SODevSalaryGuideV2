package de.hartz.software.sodevsalaryguide.application.http.config.env;

import de.hartz.software.sodevsalaryguide.core.model.helper.Route;
import de.hartz.software.sodevsalaryguide.core.port.service.RouterService;
import lombok.Data;

// TODO: Needs to be moved to spring config module so we can make it an autoconfiguration
@Data
public class LocalDeveloperRouterServiceImpl implements RouterService {

    public final Route uiBackend = new Route("localhost", 8081);
    public final Route serviceBackend = uiBackend;
    // Only route for external. TODO: Make a flag within domain model?
    public final Route frontend = new Route("localhost", 8080);

    // TODO: Theoretically SET of Ports, maybe make a getter and log a warning to use ingress or other load balancer / reverse proxy
    public final Route batch = new Route("localhost", 8082);
    public final Route database = new Route("localhost", 9999);
    public final Route AMQP = new Route("localhost", 5672);

}
