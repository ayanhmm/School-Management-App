package org.javaSchool.configs.reader.queriesConfigReader;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.javaSchool.configs.reader.Config;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueriesConfig extends Config {
    private Map<String, String> paramDatatype;
    public Map<String, QueryDefinition> components;

    /*
    Explicit no-args constructor
    Required for JSON extraction
     */
    public QueriesConfig() { }

    public Map<String, String> getParamDatatype() {
        return paramDatatype;
    }

    public void setParamDatatype(Map<String, String> paramDatatype) {
        this.paramDatatype = paramDatatype;
    }

    @Override
    public Map<String, QueryDefinition> getComponents() {
        return components;
    }

    public void setComponents(Map<String, QueryDefinition> components) {
        this.components = components;
    }

}
