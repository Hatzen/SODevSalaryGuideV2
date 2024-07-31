package de.hartz.software.sodevsalaryguide.application.http.config.env;

import de.hartz.software.sodevsalaryguide.core.model.helper.Route;
import de.hartz.software.sodevsalaryguide.core.port.service.RouterService;
import lombok.Data;

// TODO: Needs to be moved to spring config module so we can make it an autoconfiguration
@Data
public class KubernetesRouterServiceImpl implements RouterService {

    // TODO: refactore routes to get loaded from config server.
    public final Route uiBackend = new Route("ui-backend", 8121);
    public final Route serviceBackend = uiBackend;
    // Only route for external. TODO: Make a flag within domain model?
    public final Route frontend = new Route("localhost", 8121);

    // TODO: Theoretically SET of Ports, maybe make a getter and log a warning to use ingress or other load balancer / reverse proxy
    public final Route batch = new Route("application-batch-worker-intake", 8082);
    public final Route database = new Route("db", 5432);
    public final Route AMQP = new Route("rabbitmq", 5672);

}
