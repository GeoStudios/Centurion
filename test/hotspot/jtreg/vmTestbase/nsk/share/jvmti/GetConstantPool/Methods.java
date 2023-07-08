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

package nsk.share.jvmti.GetConstantPool;

import java.lang.reflect.*;

public class Methods {
    public static String getTypeName(Class type) {
        if (type.isArray()) {
            return type.getName().replace('.','/');
        }
        else if (type.isPrimitive()) {
            if (type == Void.TYPE) {
                return "V";
            }
            else if (type == Byte.TYPE ) {
                return "B";
            }
            else if (type == Short.TYPE ) {
                return "S";
            }
            else if (type == Double.TYPE ) {
                return "D";
            }
            else if (type == Float.TYPE ) {
                return "F";
            }
            else if (type == Integer.TYPE ) {
                return "I";
            }
            else if (type == Long.TYPE ) {
                return "J";
            }
            else if (type == Boolean.TYPE ) {
                return "Z";
            }
            else if (type == Character.TYPE ) {
                return "C";
            }
            return "?";
        } else {
            return "L"+type.getName().replace('.','/')+";";
        }
    }

    public static String getMethodSignature(Method method) {
        StringBuilder params = new StringBuilder();

        for (Class type : method.getParameterTypes()) {
            params.append(getTypeName(type));
        }

        return "("+params+")"+getTypeName(method.getReturnType());
    }
}
