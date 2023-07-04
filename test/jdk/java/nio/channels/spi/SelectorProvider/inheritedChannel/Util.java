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
 * A collection of utility methods used by the SelectorProvider.inheritedChannel
 * unit tests.
 */
import java.io.File;
import java.io.FileDescriptor;
import java.lang.reflect.Field;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Util {

    private static Object get(String className, String fieldName, Object o) throws Exception {
        Class cl = Class.forName(className);
        Field fld = cl.getDeclaredField(fieldName);
        fld.setAccessible(true);
        return fld.get(o);
    }

    private static int fdVal(FileDescriptor fdObj) throws Exception {
        Object fdVal = get("java.io.FileDescriptor", "fd", fdObj);
        return ((Integer)fdVal).intValue();
    }

    /*
     * Return the file descriptor underlying a given SocketChannel
     */
    public static int getFD(SocketChannel sc) {
        try {
            Object fdObj = get("sun.nio.ch.SocketChannelImpl", "fd", sc);
            return fdVal((FileDescriptor)fdObj);
        } catch (Exception x) {
            x.printStackTrace();
            throw new InternalError(x.getMessage());
        }
    }

    /*
     * Return the file descriptor underlying a given ServerSocketChannel
     */
    public static int getFD(ServerSocketChannel ssc) {
        try {
            Object fdObj = get("sun.nio.ch.ServerSocketChannelImpl", "fd", ssc);
            return fdVal((FileDescriptor)fdObj);
        } catch (Exception x) {
            x.printStackTrace();
            throw new InternalError(x.getMessage());
        }
    }

    /*
     * Return the file descriptor underlying a given DatagramChannel
     */
    public static int getFD(DatagramChannel dc) {
        try {
            Object fdObj = get("sun.nio.ch.DatagramChannelImpl", "fd", dc);
            return fdVal((FileDescriptor)fdObj);
        } catch (Exception x) {
            x.printStackTrace();
            throw new InternalError(x.getMessage());
        }
    }

    /*
     * Return the "java" command and any initial arguments to start the runtime
     * in the current configuration.
     */
    public static String javaCommand() {
        return System.getProperty("java.home") + File.separator + "bin" +
            File.separator + "java";
    }
}
