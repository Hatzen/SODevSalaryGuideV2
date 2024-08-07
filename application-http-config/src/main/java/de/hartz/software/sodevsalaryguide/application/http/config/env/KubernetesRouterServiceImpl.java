package de.hartz.software.sodevsalaryguide.application.http.config.env;

import de.hartz.software.sodevsalaryguide.core.model.helper.Route;
import de.hartz.software.sodevsalaryguide.core.port.service.RouterService;
import lombok.Data;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;

// TODO: Needs to be moved to spring config module so we can make it an autoconfiguration
@Data
public class KubernetesRouterServiceImpl implements RouterService {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public Route getUiBackend() {
        // TODO: or  ui-backend and port 8121
        return getRoute("application-http-api");
    }

    @Override
    public Route getServiceBackend() {
        return getUiBackend();
    }

    @Override
    public Route getFrontend() {
        // TODO: or ui-frontend
        return getRoute("application-http-frontend");
    }

    @Override
    public Route getBatch() {
        return getRoute("application-batch-worker-intake");
    }

    @Override
    public Route getDatabase() {
        // TODO: Or db? and port 5432 or 9999 if external is needed.
        return getRoute("postgres-database");
    }

    @Override
    public Route getAMQP() {
        // TODO: Or rabbitmq and port 5672
        return getRoute("rabbitmq3");
    }

    private Route getRoute(String kubernetesPodName) {
        val route = discoveryClient.getInstances(kubernetesPodName).get(0);
        return new Route(route.getHost(), route.getPort());
    }
}
