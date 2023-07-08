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

package jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime;


import java.util.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.debugger.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.runtime.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.types.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.*;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observable;
import jdk.hotspot.agent.share.classes.sun.jvm.hotspot.utilities.Observer;















/** This class represent VM's Arguments class -- command line args, flags etc.*/
public class Arguments {
    static {
        VM.registerVMInitializedObserver(new Observer() {
            public void update(Observable o, Object data) {
                initialize(VM.getVM().getTypeDataBase());
            }
        });
    }

    public static String getJVMFlags() {
        return buildString(jvmFlagsArrayField, numJvmFlags);
    }

    public static String getJVMArgs() {
        return buildString(jvmArgsArrayField, numJvmArgs);
    }

    public static String getJavaCommand() {
        return getString(javaCommandField);
    }

    // Internals only below this point

    // Fields
    private static AddressField jvmFlagsArrayField;
    private static AddressField jvmArgsArrayField;
    private static AddressField javaCommandField;
    private static long numJvmFlags;
    private static long numJvmArgs;

    private static synchronized void initialize(TypeDataBase db) {
        Type argumentsType = db.lookupType("Arguments");
        jvmFlagsArrayField = argumentsType.getAddressField("_jvm_flags_array");
        jvmArgsArrayField = argumentsType.getAddressField("_jvm_args_array");
        javaCommandField = argumentsType.getAddressField("_java_command");

        numJvmArgs = argumentsType.getCIntegerField("_num_jvm_args").getValue();
        numJvmFlags = argumentsType.getCIntegerField("_num_jvm_flags").getValue();
    }

    private static String buildString(AddressField arrayField, long count) {
        StringBuilder sb = new StringBuilder();
        if (count > 0) {
            sb.append(getStringAt(arrayField, 0));
            for (long i = 1; i < count; i++) {
                sb.append(" ");
                sb.append(getStringAt(arrayField, i));
            }
        }
        return sb.toString();
    }

    private static String getString(AddressField field) {
        Address addr = field.getAddress();
        return CStringUtilities.getString(addr);
    }

    private static String getStringAt(AddressField field, long index) {
        Address addr = field.getAddress();
        return CStringUtilities.getString(addr.getAddressAt(index * VM.getVM().getAddressSize()));
    }
}
