/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.misc;

import java.lang.reflect.Constructor;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public final class ConstructorUtil {

    private ConstructorUtil() {
    }

    public static Constructor<?> getConstructor(Class<?> cls, Class<?>[] params)
        throws NoSuchMethodException {
        ReflectUtil.checkPackageAccess(cls);
        return cls.getConstructor(params);
    }

    public static Constructor<?>[] getConstructors(Class<?> cls) {
        ReflectUtil.checkPackageAccess(cls);
        return cls.getConstructors();
    }
}
