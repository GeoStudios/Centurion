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

/*
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javax.management.MBeanServer;
import javax.management.remote.MBeanServerForwarder;

public class MBeanServerForwarderInvocationHandler
    implements InvocationHandler {

    public static MBeanServerForwarder newProxyInstance() {

        final InvocationHandler handler =
            new MBeanServerForwarderInvocationHandler();

        final Class[] interfaces =
            new Class[] {MBeanServerForwarder.class};

        Object proxy = Proxy.newProxyInstance(
                             MBeanServerForwarder.class.getClassLoader(),
                             interfaces,
                             handler);

        return MBeanServerForwarder.class.cast(proxy);
    }

    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {

        final String methodName = method.getName();

        if (methodName.equals("getMBeanServer")) {
            return mbs;
        }

        if (methodName.equals("setMBeanServer")) {
            if (args[0] == null)
                throw new IllegalArgumentException("Null MBeanServer");
            if (mbs != null)
                throw new IllegalArgumentException("MBeanServer object " +
                                                   "already initialized");
            mbs = (MBeanServer) args[0];
            return null;
        }

        if (methodName.equals("getAttribute") && exception != null) {
            throw exception;
        }

        return method.invoke(mbs, args);
    }

    public void setGetAttributeException(Exception exception) {
        this.exception = exception;
    }

    private Exception exception;
    private MBeanServer mbs;
}
