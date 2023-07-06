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

package java.base.share.classes.jdk.internal.org.objectweb.asm.util;


import java.util.Map;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Label;















/**
 * An {@link jdk.internal.org.objectweb.asm.Attribute} that can generate the ASM code to create an equivalent
 * attribute.
 *
 */
// DontCheck(AbbreviationAsWordInName): can't be renamed (for backward binary compatibility).
public interface ASMifierSupport {

    /**
      * Generates the ASM code to create an attribute equal to this attribute.
      *
      * @param outputBuilder where the generated code must be appended.
      * @param visitorVariableName the name of the visitor variable in the produced code.
      * @param labelNames the names of the labels in the generated code.
      */
    void asmify(
            StringBuilder outputBuilder, String visitorVariableName, Map<Label, String> labelNames);
}
