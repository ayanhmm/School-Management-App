package org.javaSchool.utils;

import java.util.HashMap;
import java.util.Map;

public enum JsonInputFields {
        CANNOT_DETERMINE_FIELD("Field not identified"),
        STUDENT_MANAGEMENT_ACTION("Student Management Action"),
        ENTITY_ID("ID"),
        ENTITY_NAME("Name");

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
