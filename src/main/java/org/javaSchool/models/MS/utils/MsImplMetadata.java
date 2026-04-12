package org.javaSchool.models.MS.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
Meta annotations:: Annotations that you apply to other annotations to define how they behave.

RetentionPolicy.RUNTIME
- It ensures the annotation is recorded in the .class file and is available to the JVM ar runtime
- Without this, you cannot use retention
RetentionPolicy.CLASS (The Default)
- The annotation is recorded in the .class file by the compiler, but it is not loaded into memory
RetentionPolicy.SOURCE
- The annotation is only visible in the source code (.java file) and is discarded by the compiler during the build process.

ElementType.TYPE
It restricts the annotation so it can only be used classes/interfaces/enums
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
/*
This annotation is something like the @Singleton
 */
public @interface MsImplMetadata {
    /*
    This defines an attribute called 'value'
    Adding default makes it an optional attribute

    Primary attribute::
    The attribute that gets assigned when a value passed directly
    like in @Target(ElementType.TYPE)

    The Primary attribute is by default names value
     */
    Class<?> value();
    String description() default "";
}
