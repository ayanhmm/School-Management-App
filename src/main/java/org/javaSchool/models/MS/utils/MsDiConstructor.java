package org.javaSchool.models.MS.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
This annotation specifies which constructor will be used when lazy loading a ms
Somewhat like @Inject
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
public @interface MsDiConstructor {
}
