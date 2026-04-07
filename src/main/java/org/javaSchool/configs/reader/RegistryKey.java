package org.javaSchool.configs.reader;

import java.util.Objects;

public class RegistryKey {
    private final String type;
    private final String subtype;
    private final String name;

    public RegistryKey(String type, String subtype, String name) {
        this.type = type;
        this.subtype = subtype;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistryKey)) return false;
        RegistryKey that = (RegistryKey) o;
        return type.equals(that.type) &&
                subtype.equals(that.subtype) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, subtype, name);
    }
}
