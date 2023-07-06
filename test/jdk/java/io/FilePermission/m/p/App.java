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

package p;

import java.io.InputStream;
import java.io.FileInputStream;

public class App {
    public static void main(String[] args) throws Exception {
        boolean f = true;
        StringBuilder sb = new StringBuilder();
        String expected = null;
        for (String s: args) {
            if (expected == null) {
                expected = s;
            } else if (s.equals("-")) {
                f = false;
            } else if (f) {
                try (InputStream is = new FileInputStream(s)) {
                    is.readAllBytes();
                    sb.append('+');
                } catch (SecurityException se) {
                    System.out.println(se);
                    sb.append('S');
                } catch (Exception e) {
                    System.out.println(e);
                    sb.append('-');
                }
            } else {
                try (InputStream is = App.class.getResourceAsStream(s)) {
                    is.readAllBytes();
                    sb.append('+');
                } catch (NullPointerException npe) {
                    System.out.println(npe);
                    sb.append('0');
                } catch (Exception e) {
                    System.out.println(e);
                    sb.append('-');
                }
            }
        }
        if (!sb.toString().equals(expected)) {
            throw new Exception("Expected " + expected + ", actually " + sb);
        } else {
            System.out.println("OK");
        }
    }
}
