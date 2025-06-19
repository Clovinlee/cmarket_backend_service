package com.chris.cmarket.Infrastructure.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import com.chris.cmarket.Common.Contract.RouteConfigInterface;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class RouteConfig {

    /**
     * An array of route configurations that will be applied to the HttpSecurity
     * instance.
     * Each RouteConfigInterface implementation defines specific routes and their
     * security settings.
     */
    private final RouteConfigInterface[] routeConfigs;

    /**
     * Initializes the routes for the application.
     * This method iterates through all registered route configurations and applies
     * them to the provided HttpSecurity instance.
     *
     * @param http the HttpSecurity instance to configure
     * @throws Exception if an error occurs during route configuration
     */
    public void initializeRoutes(HttpSecurity http) throws Exception {
        for (RouteConfigInterface routeConfig : routeConfigs) {
            routeConfig.configureRoute(http);
        }
    }
}
