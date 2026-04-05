package org.javaSchool.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public enum JsonOutputFields {
    ERROR_MESSAGE("Error Message"),
    ERROR_CODE("Error Code"),
    SUCCESS_BOOLEAN("Success Boolean"),
    REQUEST_PARAMS("Request params"),
    REQUEST_RESULT("Request result"),
    CANNOT_DETERMINE_FIELD("Field not identified");

    private final String value;
    private static final Map<String, JsonOutputFields> LOOKUP = new HashMap<>();
    static {
        for (JsonOutputFields field : values()) {
            LOOKUP.put(field.value, field);
        }
    }

    JsonOutputFields(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static JsonOutputFields fromString(String value){
        return LOOKUP.getOrDefault(value, CANNOT_DETERMINE_FIELD);
    }
}
