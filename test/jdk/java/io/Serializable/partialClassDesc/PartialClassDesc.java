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

/* @test
 * @bug 4373844
 * @summary Verify that partially initialized ObjectStreamClass instances
 *          cannot be obtained from ObjectStreamClass.lookup() in the event
 *          that the target class is uninitializable.
 */

import java.io.*;

class A implements Serializable {
    // existence of SVUID forces class initialization during classdesc init
    private static final long serialVersionUID = 0L;

    static {
        if ("foo".equals("foo")) {      // force class initialization failure
            throw new Error();
        }
    }
}

public class PartialClassDesc {
    public static void main(String[] args) throws Exception {
        Class<?> cl = Class.forName(
            "A", false, PartialClassDesc.class.getClassLoader());
        ObjectStreamClass desc = null;
        try {
            desc = ObjectStreamClass.lookup(cl);
        } catch (Throwable th) {
        }
        try {
            desc = ObjectStreamClass.lookup(cl);
        } catch (Throwable th) {
        }
        if (desc != null) {
            throw new Error("should not be able to obtain class descriptor");
        }
    }
}
