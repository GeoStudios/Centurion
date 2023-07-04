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

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class TransferableList extends ArrayList {
    private static class NullInvocationHandler implements InvocationHandler, Serializable {
        public Object invoke(Object proxy, Method method, Object[] args)
          throws Throwable {
            throw new Error("UNIMPLEMENTED");
        }
    }

    public TransferableList() {
        try {
            InvocationHandler handler = new NullInvocationHandler();
            Class<?> proxyClass = Proxy.getProxyClass(
                ListInterface.class.getClassLoader(),
                new Class[] { ListInterface.class, AnotherInterface.class });
            AnotherInterface obj = (AnotherInterface) proxyClass.
                    getConstructor(new Class[]{InvocationHandler.class}).
                    newInstance(handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

interface ListInterface extends Serializable {}
