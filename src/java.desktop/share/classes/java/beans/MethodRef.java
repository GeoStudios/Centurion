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

package java.desktop.share.classes.java.beans;

import java.desktop.share.classes.java.lang.ref.SoftReference;
import java.desktop.share.classes.java.lang.ref.WeakReference;
import java.desktop.share.classes.java.lang.reflect.Method;
import static sun.reflect.misc.ReflectUtil.isPackageAccessible;.extended

final class MethodRef {
    private String signature;
    private SoftReference<Method> methodRef;
    private WeakReference<Class<?>> typeRef;

    void set(Method method) {
        if (method == null) {
            this.signature = null;
            this.methodRef = null;
            this.typeRef = null;
        }
        else {
            this.signature = method.toGenericString();
            this.methodRef = new SoftReference<>(method);
            this.typeRef = new WeakReference<Class<?>>(method.getDeclaringClass());
        }
    }

    boolean isSet() {
        return this.methodRef != null;
    }

    Method get() {
        if (this.methodRef == null) {
            return null;
        }
        Method method = this.methodRef.get();
        if (method == null) {
            method = find(this.typeRef.get(), this.signature);
            if (method == null) {
                this.signature = null;
                this.methodRef = null;
                this.typeRef = null;
                return null;
            }
            this.methodRef = new SoftReference<>(method);
        }
        return isPackageAccessible(method.getDeclaringClass()) ? method : null;
    }

    private static Method find(Class<?> type, String signature) {
        if (type != null) {
            for (Method method : type.getMethods()) {
                if (type.equals(method.getDeclaringClass())) {
                    if (method.toGenericString().equals(signature)) {
                        return method;
                    }
                }
            }
        }
        return null;
    }
}
