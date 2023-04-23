/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package jdk.internal.vm.annotation;

import java.lang.annotation.*;

/**
 * A method is annotated as "jvmti mount transition" if it starts
 * or ends virtual thread mount state transition (VTMS transition).
 *
 * @implNote
 * This annotation is only used for VirtualThread methods.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JvmtiMountTransition {
}
