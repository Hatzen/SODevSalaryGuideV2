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
        val route = discoveryClient.getInstances("application-http-api").get(0);
        return new Route(route.getHost(), route.getPort());
    }

    @Override
    public Route getServiceBackend() {
        return null;
    }

    @Override
    public Route getFrontend() {
        return null;
    }

    @Override
    public Route getBatch() {
        return null;
    }

    @Override
    public Route getDatabase() {
        return null;
    }

    @Override
    public Route getAMQP() {
        return null;
    }
}
