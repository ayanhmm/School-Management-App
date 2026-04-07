package org.javaSchool.configs.reader;

import java.util.HashMap;
import java.util.Map;

public class ConfigRegistry {
    private static final Map<RegistryKey, Object> REGISTRY = new HashMap<>();

    public static void addConfigToRegistry(String type, String subtype, String name, Object value){
        RegistryKey registryKey = new RegistryKey(type, subtype, name);
        REGISTRY.put(registryKey, value);
    }

    public static Object getConfigFromRegistry(String type, String subtype, String name){
        RegistryKey registryKey = new RegistryKey(type, subtype, name);
        return REGISTRY.getOrDefault(registryKey, null);
    }

    public static Map<RegistryKey, Object> getRegistry(){
        return REGISTRY;
    }
}
