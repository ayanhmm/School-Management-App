package org.javaSchool.models.MS.utils;

import org.javaSchool.models.MS.MicroService;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/*
This is somewhat like quarkus Arc CDI
 */
public class MsRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(MsRegistry.class);
    private static final Map<Class<?>, Object> REGISTRY = new HashMap<>();

    public static <T> void register(Class<T> className, Object classObject){
        REGISTRY.put(className, classObject);
    }

    public static <T> Object get(Class<T> className){
        if(!REGISTRY.containsKey(className)) {
            try{
                Object classObject = getImpl(className);
                register(className, classObject);
                LOGGER.info("Successfully Created MsImpl {}, registry size {}", className, REGISTRY.size());
            }
            catch (Exception ex){
                LOGGER.warn("failed to create instance of service", ex);
            }
        }
        return REGISTRY.getOrDefault(className, null);
    }

    private static  <T> Object getImpl(Class<T> className)
    throws Exception{
        Reflections reflections = new Reflections("org.javaSchool.models.MS.impl");
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(MsImplMetadata.class);
        LOGGER.debug("Reflections found classes with annotation MsImplMetadata {}", annotatedClasses);

        for(Class<?> currentClass : annotatedClasses){
            if(currentClass.getAnnotation(MsImplMetadata.class).value() == className){
                LOGGER.debug("successfully matched {} MsImplMetadata for class {}",className, currentClass);
                return currentClass.getDeclaredConstructor().newInstance();
            }
        }

        throw new Exception("Unable to get implication");
    }
}
