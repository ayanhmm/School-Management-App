package org.javaSchool.utils.jsonIO;

import java.util.HashMap;
import java.util.Map;

public enum JsonOutputFields {
    ERROR_MESSAGE(JsonFieldsTextValues.ERROR_MESSAGE),
    ERROR_CODE(JsonFieldsTextValues.ERROR_CODE),
    SUCCESS_BOOLEAN(JsonFieldsTextValues.SUCCESS_BOOLEAN),

    REQUEST_PARAMS(JsonFieldsTextValues.REQUEST_PARAMS),
    REQUEST_RESULT(JsonFieldsTextValues.REQUEST_RESULT),

    ENTITY_ID(JsonFieldsTextValues.ENTITY_ID),
    ENTITY_NAME(JsonFieldsTextValues.ENTITY_NAME),

    CANNOT_DETERMINE_FIELD(JsonFieldsTextValues.CANNOT_DETERMINE_FIELD);

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
