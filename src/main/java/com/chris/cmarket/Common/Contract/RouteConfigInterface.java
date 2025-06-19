package com.chris.cmarket.Common.Contract;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface RouteConfigInterface {
    /**
     * Configures the route for the application.
     * This method should be implemented to set up the necessary routes and their
     * configurations.
     *
     * @throws Exception if an error occurs during route configuration
     */
    void configureRoute(HttpSecurity http) throws Exception;
}
