package org.javaSchool.configs.reader.queriesConfigReader;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueriesConfig {
    private String type;
    private String subtype;
    private Map<String, String> params;
    private Map<String, QueryDefinition> queries;

    /*
    Explicit no-args constructor
    Required for JSON extraction
     */
    public QueriesConfig() { }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Map<String, QueryDefinition> getQueries() {
        return queries;
    }

    public void setQueries(Map<String, QueryDefinition> queries) {
        this.queries = queries;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
}
