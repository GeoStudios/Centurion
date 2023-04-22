/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.misc;

import java.lang.reflect.Field;

/**
 * Create a trampoline class.
 * 
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public final class FieldUtil {

    private FieldUtil() {
    }

    public static Field getField(Class<?> cls, String name)
        throws NoSuchFieldException {
        ReflectUtil.checkPackageAccess(cls);
        return cls.getField(name);
    }

    public static Field[] getFields(Class<?> cls) {
        ReflectUtil.checkPackageAccess(cls);
        return cls.getFields();
    }
}
