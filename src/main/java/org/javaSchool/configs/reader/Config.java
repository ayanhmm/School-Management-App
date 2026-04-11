package org.javaSchool.configs.reader;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.javaSchool.configs.reader.databaseConnectivityConfigsReader.ConnectionPropertiesConfig;
import org.javaSchool.configs.reader.queriesConfigReader.QueriesConfig;

import java.util.Map;

/*
@JsonTypeInfo looks like
- use :: This defines how the type is identified. Id.NAME - via logical name
- include :: This defines where the type information is located. As.PROPERTY means the type info is just another field inside the JSON object itself.
- property :: name of json field that defines the name
- visible = true :: means config should also extract the field declared in "property" -> skipped by default
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = QueriesConfig.class, name = "Queries_Config"),
        @JsonSubTypes.Type(value = ConnectionPropertiesConfig.class, name = "Connection_Properties_Config")
})
public abstract class Config {
    public String type;
    public String subtype;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public abstract Map<String, ?> getComponents();
}
