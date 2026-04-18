package org.javaSchool.models.MS.utils;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/*
This is somewhat like quarkus Arc CDI
While individual instances created are meant to represent "beans"
 */
public class MsRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(MsRegistry.class);
    private static final Map<Class<?>, Object> REGISTRY = new HashMap<>();
    private static final Map<Class<?>, Class<?>> MS_TO_IMPL = new HashMap<>();
    private static final Set<Class<?>> CURRENTLY_CREATING = new HashSet<>();
 /*
 these methods called exactly once when class is first formed
  */
    static {
        generateMsToImpl();
    }

    public static <T> void register(Class<T> className, Object classObject){
        REGISTRY.put(className, classObject);
    }

    public static <T> Object get(Class<T> msClass){
        if(!REGISTRY.containsKey(msClass)) lazyLoad(msClass);
        return REGISTRY.getOrDefault(msClass, null);
    }

    private static <T> void lazyLoad(Class<T> msClass){
        if(CURRENTLY_CREATING.contains(msClass)){
            CURRENTLY_CREATING.clear();
            LOGGER.warn("Circular dependency detected while creating {}", msClass);
        }
        CURRENTLY_CREATING.add(msClass);

        try{
            Object classObject = getImpl(msClass);
            register(msClass, classObject);
            LOGGER.info("Successfully Created MsImpl {}, registry size {}", msClass, REGISTRY.size());
            CURRENTLY_CREATING.remove(msClass);
        }
        catch (Exception ex){
            LOGGER.warn("failed to create instance of service", ex);
        }

    }

    private static  <T> Object getImpl(Class<T> msClass)
    throws Exception{
        if(MS_TO_IMPL.containsKey(msClass)){
            Class<?> implClass = MS_TO_IMPL.get(msClass);
            Constructor<?> constructor = findConstructor(implClass);
            Class<?>[] paramTypes = constructor.getParameterTypes();
            Object[] dependencies = new Object[paramTypes.length];

            for (int i = 0; i < paramTypes.length; i++) {
                dependencies[i] = get(paramTypes[i]);
            }

            return constructor.newInstance(dependencies);
        }
        throw new Exception("Unable to get implication");
    }

    private static Constructor<?> findConstructor(Class<?> clazz) throws NoSuchMethodException {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            if (constructor.isAnnotationPresent(MsDiConstructor.class)) {
                return constructor;
            }
        }
        return clazz.getDeclaredConstructor();
    }

    private static void generateMsToImpl(){
        Reflections reflections = new Reflections("org.javaSchool.models.MS.impl");
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(MsImplMetadata.class);
        for(Class<?> implClass : annotatedClasses){
            MS_TO_IMPL.put(implClass.getAnnotation(MsImplMetadata.class).value(), implClass);
        }
    }
}
