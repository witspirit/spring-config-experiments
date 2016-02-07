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

import java.util.ArrayList;
import java.util.List;

@RestController
public class PropertyExposure {
    private static final Logger LOG = LoggerFactory.getLogger(PropertyExposure.class);

    @Autowired
    private Environment env;

    @Autowired
    private ConfigValues configValues;

    @RequestMapping("/")
    public String listConfigured() {
        List<String> props = new ArrayList<>();
        props.add(prop("application.title"));
        props.add(prop("environment"));
        props.add(prop("deployment.target"));
        props.add(prop("common.property"));
        props.add(prop("another.common.property"));
        props.add(prop("configclient.specific.property"));
        props.add(prop("configclient.specific.favorite.jdk"));
        props.add(prop("configserver.specific.property"));
        props.add(prop("configserver.specific.favorite.framework"));
        return "<ul>" + props.stream().map(s -> "<li>"+s+"</li>").reduce((s1, s2) -> s1+"\n"+s2).get()+"</ul>";
    }

    private String prop(String propName) {
        return propName + " : "+ env.getProperty(propName, "UNSET");
    }



    @RequestMapping("/profiles")
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
