package org.javaSchool.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public class ApiExecutor {
    public static final Logger LOGGER = LoggerFactory.getLogger(ApiExecutor.class);

    /*
    The <> at the beginning tell the compiler that the method uses these placeholders for currently unknown datatypes
    These are called "generics"

    Function<T, R> represents a block of code which will take input T are yield result R
    The block can be a method/lambdafunction/etc
    Strictly defined for one input and one output, for 2 inputs, may use BiFunction<T, U, R>

    method.apply(param) means call the method with input "param"
     */
    public static <T, R> R invokeService(Class<T> serviceClass, Function<T, R> method){
        T proxy = MsProxyProvider.createProxy(serviceClass);
        LOGGER.info("invoking proxy for service: {}", proxy);
//        even proxy.string() calls the invocation handler, hence logging only proxy not proxy.tostring();

        return method.apply(proxy);
    }
}
