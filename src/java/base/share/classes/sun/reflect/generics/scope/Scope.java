/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.scope;

import java.lang.reflect.TypeVariable;

/**
* @since Java 2
* @author Logan Abernathy
* @edited 22/4/2023 
*/

public interface Scope {
    TypeVariable<?> lookup(String name);
}
