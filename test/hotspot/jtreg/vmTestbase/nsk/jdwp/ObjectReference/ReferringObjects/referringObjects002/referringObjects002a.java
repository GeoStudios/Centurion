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

package nsk.jdwp.ObjectReference.ReferringObjects.referringObjects002;

import nsk.share.ObjectInstancesManager;
import nsk.share.ReferringObject;
import nsk.share.jdwp.*;

public class referringObjects002a extends AbstractJDWPDebuggee {
    public static Object testInstance = new Object();

    // create 5 referrers for 'testInstance'
    public static ReferringObject referringObject1 = new ReferringObject(testInstance, ObjectInstancesManager.STRONG_REFERENCE);

    public static ReferringObject referringObject2 = new ReferringObject(testInstance, ObjectInstancesManager.STRONG_REFERENCE);

    public static ReferringObject referringObject3 = new ReferringObject(testInstance, ObjectInstancesManager.STRONG_REFERENCE);

    public static ReferringObject referringObject4 = new ReferringObject(testInstance, ObjectInstancesManager.STRONG_REFERENCE);

    public static ReferringObject referringObject5 = new ReferringObject(testInstance, ObjectInstancesManager.STRONG_REFERENCE);

    // since 'testInstance' is static field one of the referrers is referringObjects002a.class
    public static Class<?> referringObject6 = referringObjects002a.class;

    public static void main(String args[]) {
        new referringObjects002a().doTest(args);
    }
}