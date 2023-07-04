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
 * @test
 * @bug 6544278
 * @summary Confirm the JarInputStream throws the SecurityException when
 *          verifying an indexed jar file with corrupted signature
 */

import java.io.IOException;
import java.io.FileInputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class TestIndexedJarWithBadSignature {

    public static void main(String...args) throws Throwable {
        try (JarInputStream jis = new JarInputStream(
                 new FileInputStream(System.getProperty("test.src", ".") +
                                     System.getProperty("file.separator") +
                                     "BadSignedJar.jar")))
        {
            JarEntry je1 = jis.getNextJarEntry();
            while(je1!=null){
                System.out.println("Jar Entry1==>"+je1.getName());
                je1 = jis.getNextJarEntry(); // This should throw Security Exception
            }
            throw new RuntimeException(
                "Test Failed:Security Exception not being thrown");
        } catch (IOException ie){
            ie.printStackTrace();
        } catch (SecurityException e) {
            System.out.println("Test passed: Security Exception thrown as expected");
        }
    }
}
