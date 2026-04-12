package org.javaSchool.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MsInvocationHandler implements InvocationHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MsInvocationHandler.class);

    private final Object targetService;
    public MsInvocationHandler(Object targetService) {
        this.targetService = targetService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOGGER.info("Executing MsInvocationHandler...");
        //TODO :: handle grpc requests if method not present locally
        return method.invoke(targetService, args);
    }
}
