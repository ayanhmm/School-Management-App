package org.javaSchool.configs.reader.queriesConfigReader;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDefinition {
    private String query;
    private List<String> params;

    public QueryDefinition() { } // Explicit no-args constructor
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}
