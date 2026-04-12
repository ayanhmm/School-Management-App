package org.javaSchool.api.utils;

import org.javaSchool.models.MS.SchoolAppMs;
import org.javaSchool.models.MS.StudentManagementMs;
import org.javaSchool.models.MS.impl.SchoolAppMsImpl;
import org.javaSchool.models.MS.impl.StudentManagementMsImpl;
import org.javaSchool.models.MS.utils.MsRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsProxyProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(MsProxyProvider.class);

    public static <T> T createProxy(Class<T> interfaceClass) {
        MsInvocationHandler msInvocationHandler = getMsInvocationHandler(interfaceClass);
        return (T) java.lang.reflect.Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                msInvocationHandler
        );
    }

    private static <T> MsInvocationHandler getMsInvocationHandler(Class<T> interfaceClass){
        LOGGER.debug("Fetching apt MsInvocationHandler");
        return new MsInvocationHandler(MsRegistry.get(interfaceClass));
    }

}
