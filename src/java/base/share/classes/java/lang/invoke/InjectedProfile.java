/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang.invoke;

import java.lang.annotation.*;

/**
 * Internal marker for some methods in the JSR 292 implementation.
 */
/*non-public*/
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@interface InjectedProfile {
}
