package org.javaSchool.configs.reader.databaseConnectivityConfigsReader;

import java.util.Map;

public class ConnectionDefinition {
    private String classname;
    private Map<String, String> credentials;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Map<String, String> getCredentials() {
        return credentials;
    }

    public void setCredentials(Map<String, String> credentials) {
        this.credentials = credentials;
    }
}
