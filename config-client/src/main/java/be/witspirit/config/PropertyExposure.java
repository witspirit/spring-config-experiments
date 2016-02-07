package be.witspirit.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyExposure {
    private static final Logger LOG = LoggerFactory.getLogger(PropertyExposure.class);

    @Autowired
    private Environment env;

    @Autowired
    private ConfigValues configValues;

    @RequestMapping("/")
    public String profiles() {
        LOG.info("profiles");
        return configValues.getTitle() + " on " + configValues.getDeploymentTarget();
    }

    @RequestMapping("/fromenv")
    public String fromenv() {
        LOG.info("fromenv");
        return env.getProperty("application.title")+" - "+env.getProperty("environment");
    }

    @RequestMapping("/common")
    public String common() {
        LOG.info("common");
        return env.getProperty("common.property");
    }

    @RequestMapping("/configvalues")
    public String configValues() {
        LOG.info("configValues");
        return configValues.getTitle() + " - " + configValues.getEnvironment() + "(" + configValues.getCommonProperty()+")";
    }

}
