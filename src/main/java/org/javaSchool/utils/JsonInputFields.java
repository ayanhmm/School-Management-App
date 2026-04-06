package org.javaSchool.utils;

import java.util.HashMap;
import java.util.Map;

public enum JsonInputFields {
    CANNOT_DETERMINE_FIELD(JsonFieldsTextValues.CANNOT_DETERMINE_FIELD),
    STUDENT_MANAGEMENT_ACTION(JsonFieldsTextValues.STUDENT_MANAGEMENT_ACTION),
    ENTITY_ID(JsonFieldsTextValues.ENTITY_ID),
    ENTITY_NAME(JsonFieldsTextValues.ENTITY_NAME);

    private final String value;

    private static final Map<String, JsonInputFields> LOOKUP = new HashMap<>();
    static {
    for (JsonInputFields field : values()) {
        LOOKUP.put(field.value, field);
        }
    }

    JsonInputFields(String value) {
        this.value = value;
    }

        public String getValue(){
        return value;
    }

        public static JsonInputFields fromString(String value){
        return LOOKUP.getOrDefault(value, CANNOT_DETERMINE_FIELD);
    }
}
