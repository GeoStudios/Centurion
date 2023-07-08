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

package compiler.lib.ir_framework;


import compiler.lib.ir_framework.shared.TestFormatException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;














/**
 * Force a compilation of the static class initializer method ({@code <clinit>}) of the annotated test or helper class
 * immediately at the specified level. {@link CompLevel#SKIP} and {@link CompLevel#WAIT_FOR_COMPILATION} do not apply
 * and result in a {@link TestFormatException}.

 * <p>
 *  Using this annotation on non-classes also results in a {@link TestFormatException}.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ForceCompileClassInitializer {
    /**
     * The compilation level to compile the static class initializer method ({@code <clinit>}) at.
     */
    CompLevel value() default CompLevel.ANY;
}
