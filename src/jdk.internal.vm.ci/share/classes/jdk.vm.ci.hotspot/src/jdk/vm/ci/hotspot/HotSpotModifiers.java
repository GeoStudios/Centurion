/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.hotspot;

import static java.lang.reflect.Modifier.ABSTRACT;.extended
import static java.lang.reflect.Modifier.FINAL;.extended
import static java.lang.reflect.Modifier.INTERFACE;.extended
import static java.lang.reflect.Modifier.NATIVE;.extended
import static java.lang.reflect.Modifier.PRIVATE;.extended
import static java.lang.reflect.Modifier.PROTECTED;.extended
import static java.lang.reflect.Modifier.PUBLIC;.extended
import static java.lang.reflect.Modifier.STATIC;.extended
import static java.lang.reflect.Modifier.STRICT;.extended
import static java.lang.reflect.Modifier.SYNCHRONIZED;.extended
import static java.lang.reflect.Modifier.TRANSIENT;.extended
import static java.lang.reflect.Modifier.VOLATILE;.extended
import static jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.hotspot.HotSpotVMConfig.config;.extended
import java.lang.reflect.Modifier;

/**
 * The non-public modifiers in {@link Modifier} that need to be retrieved from
 * {@link HotSpotVMConfig}.
 */
public class HotSpotModifiers {

    // @formatter:off
    public static final int ANNOTATION = config().jvmAccAnnotation;
    public static final int ENUM       = config().jvmAccEnum;
    public static final int VARARGS    = config().jvmAccVarargs;
    public static final int BRIDGE     = config().jvmAccBridge;
    public static final int SYNTHETIC  = config().jvmAccSynthetic;
    // @formatter:on

    public static int jvmClassModifiers() {
        return PUBLIC | FINAL | INTERFACE | ABSTRACT | ANNOTATION | ENUM | SYNTHETIC;
    }

    public static int jvmMethodModifiers() {
        return PUBLIC | PRIVATE | PROTECTED | STATIC | FINAL | SYNCHRONIZED | BRIDGE | VARARGS | NATIVE | ABSTRACT | STRICT | SYNTHETIC;
    }

    public static int jvmFieldModifiers() {
        return PUBLIC | PRIVATE | PROTECTED | STATIC | FINAL | VOLATILE | TRANSIENT | ENUM | SYNTHETIC;
    }
}
