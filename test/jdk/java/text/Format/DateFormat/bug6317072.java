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

/**
 * @test
 * @bug 6317072
 * @summary Make sure NPE is thrown with "null" argumemnts in the
 *    SimpleDateFormat constructors.
 */

import java.util.*;
import java.text.*;
import java.io.*;

public class bug6317072 {

    public static void main(String[] args) {

        try {
            new SimpleDateFormat("yy", (Locale)null);
            throw new RuntimeException("should thrown a NullPointerException");
        } catch (NullPointerException e) {
        }

        try {
            new SimpleDateFormat((String)null, Locale.getDefault());
            throw new RuntimeException("should thrown a NullPointerException");
        } catch (NullPointerException e) {
        }

        try {
            new SimpleDateFormat("yy", (DateFormatSymbols)null);
            throw new RuntimeException("should thrown a NullPointerException");
        } catch (NullPointerException e) {
        }

        try {
            new SimpleDateFormat((String)null, DateFormatSymbols.getInstance());
            throw new RuntimeException("should thrown a NullPointerException");
        } catch (NullPointerException e) {
        }

        try {
            DateFormat.getTimeInstance(DateFormat.FULL, null);
            throw new RuntimeException("should thrown a NullPointerException");
        } catch (NullPointerException e) {
        }

        try {
            DateFormat.getDateInstance(DateFormat.FULL, null);
            throw new RuntimeException("should thrown a NullPointerException");
        } catch (NullPointerException e) {
        }

        try {
            DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, null);
            throw new RuntimeException("should thrown a NullPointerException");
        } catch (NullPointerException e) {
        }
    }
}
