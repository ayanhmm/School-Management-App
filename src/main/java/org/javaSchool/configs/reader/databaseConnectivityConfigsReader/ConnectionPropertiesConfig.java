package org.javaSchool.configs.reader.databaseConnectivityConfigsReader;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.javaSchool.configs.reader.Config;

import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionPropertiesConfig extends Config {
    private Map<String, ConnectionDefinition> components;

    public ConnectionPropertiesConfig(){}

    @Override
    public Map<String, ConnectionDefinition> getComponents() {
        return components;
    }

    public void setComponents(Map<String, ConnectionDefinition> components) {
        this.components = components;
    }
}
