package be.witspirit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyExposure {

    @Autowired
    private ConfigurableEnvironment env;

    @RequestMapping("/")
    public String home() {
        return env.getProperty("application.title")+" - "+env.getProperty("environment");
    }

    @RequestMapping("/common")
    public String common() {
        return env.getProperty("common.property");
    }

    @RequestMapping("/sources")
    public String sources() {
        return env.getPropertySources().toString();
    }
}
