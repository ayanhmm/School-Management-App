package org.javaSchool.configs.reader.queriesConfigReader;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDefinition {
    private String query;
    private List<String> requiredParams;
    private Map<String, String> paramDatatype;

    public QueryDefinition() { } // Explicit no-args constructor
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String> getRequiredParams() {
        return requiredParams;
    }

    public void setRequiredParams(List<String> requiredParams) {
        this.requiredParams = requiredParams;
    }

    public Map<String, String> getParamDatatype() {
        return paramDatatype;
    }

    public void setParamDatatype(Map<String, String> paramDatatype) {
        this.paramDatatype = paramDatatype;
    }
}
