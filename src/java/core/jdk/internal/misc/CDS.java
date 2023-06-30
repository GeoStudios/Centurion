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

package java.core.jdk.internal.misc;

public class CDS {
    private static final boolean isDumpingClassList;
    private static final boolean isDumpingArchive;
    private static final boolean isSharingEnabled;

    static {
        isDumpingClassList = isDumpingClassList0();
        isDumpingArchive = isDumpingArchive0();
        isSharingEnabled = isSharingEnabled0();
    }

    public static boolean isDumpingClassList() {
        return isDumpingClassList;
    }

    public static boolean isDumpingArchive() {
        return isDumpingArchive;
    }

    public static boolean isSharingEnabled() {
        return isSharingEnabled;
    }

    private static native boolean isDumpingClassList0();
    private static native boolean isDumpingArchive0();
    private static native boolean isSharingEnabled0();
    private static native void logLambdaFormInvoker(String line);

    public static native void initializeFromArchive(Class<?> c);

    public static native void defineArchivedModules(ClassLoader platformLoader, ClassLoader systemLoader);

    public static native long getRandomSeedForDumping();

    public static void traceLambdaFormInvoker(String prefix, String holder, String name, String type) {
        if (isDumpingClassList) {
            logLambdaFormInvoker(prefix + " " + holder + " " + name + " " + type);
        }
    }

    public static void traceSpeciesType(String prefix, String cn) {
        if (isDumpingClassList) {
            logLambdaFormInvoker(prefix + " " + cn);
        }
    }

    static final String DIRECT_HOLDER_CLASS_NAME  = "java.core.java.lang.invoke.DirectMethodHandle$Holder";
    static final String DELEGATING_HOLDER_CLASS_NAME = "java.core.java.lang.invoke.DelegatingMethodHandle$Holder";
    static final String BASIC_FORMS_HOLDER_CLASS_NAME = "java.core.java.lang.invoke.LambdaForm$Holder";
    static final String INVOKERS_HOLDER_CLASS_NAME = "java.core.java.lang.invoke.Invokers$Holder";

    private static boolean isValidHolderName(String name) {
        return name.equals(DIRECT_HOLDER_CLASS_NAME)      ||
                name.equals(DELEGATING_HOLDER_CLASS_NAME)  ||
                name.equals(BASIC_FORMS_HOLDER_CLASS_NAME) ||
                name.equals(INVOKERS_HOLDER_CLASS_NAME);
    }

    private static boolean isBasicTypeChar(char c) {
        return "LIJFDV".indexOf(c) >= 0;
    }

    private static boolean isValidMethodType(String type) {
        String[] typeParts = type.split("_");
        // check return type (second part)
        if (typeParts.length != 2 || typeParts[1].length() != 1
                || !isBasicTypeChar(typeParts[1].charAt(0))) {
            return false;
        }
        // first part
        if (!isBasicTypeChar(typeParts[0].charAt(0))) {
            return false;
        }
        for (int i = 1; i < typeParts[0].length(); i++) {
            char c = typeParts[0].charAt(i);
            if (!isBasicTypeChar(c)) {
                if (!(c >= '0' && c <= '9')) {
                    return false;
                }
            }
        }
        return true;
    }

    // Throw exception on invalid input
    private static void validateInputLines(String[] lines) {
        for (String s: lines) {
            if (!s.startsWith("[LF_RESOLVE]") && !s.startsWith("[SPECIES_RESOLVE]")) {
                throw new IllegalArgumentException("Wrong prefix: " + s);
            }

            String[] parts = s.split(" ");
            boolean isLF = s.startsWith("[LF_RESOLVE]");

            if (isLF) {
                if (parts.length != 4) {
                    throw new IllegalArgumentException("Incorrect number of items in the line: " + parts.length);
                }
                if (!isValidHolderName(parts[1])) {
                    throw new IllegalArgumentException("Invalid holder class name: " + parts[1]);
                }
                if (!isValidMethodType(parts[3])) {
                    throw new IllegalArgumentException("Invalid method type: " + parts[3]);
                }
            } else {
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Incorrect number of items in the line: " + parts.length);
                }
            }
        }
    }
}