package be.witspirit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Attempt to capture the config values in a different way
 */
@RefreshScope
@Component
public class ConfigValues {

    @Value("${application.title:UNSET}")
    private String title;

    @Value("${environment:UNSET}")
    private String environment;

    @Value("${common.property:UNSET}")
    private String commonProperty;

    @Value("${deployment.target:UNSET}")
    private String deploymentTarget;

    public String getTitle() {
        return title;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getCommonProperty() {
        return commonProperty;
    }

    public String getDeploymentTarget() {
        return deploymentTarget;
    }
}
