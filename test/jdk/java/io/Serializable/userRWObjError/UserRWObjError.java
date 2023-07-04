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
 * @bug 4082734
 * @summary Ensure that Error exception is propogated from Serializable class'
 *          readObject & writeObject method.
 */
import java.io.*;

/*
 * Failure output:
 * joef/scarry>java UserRWObjError
 * Test FAILED:
 *  java.lang.ClassCastException: java.lang.OutOfMemoryError
 *       at java.io.ObjectOutputStream.invokeObjectWriter(ObjectOutputStream.java:1379)
 *       at java.io.ObjectOutputStream.outputObject(ObjectOutputStream.java:755)
 *       at java.io.ObjectOutputStream.writeObject(Objec
 */

public class UserRWObjError implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) throws Exception {
        try {
            UserRWObjError obj = new UserRWObjError();
            ObjectOutputStream out =
                new ObjectOutputStream(new ByteArrayOutputStream());
            out.writeObject(obj);
        } catch (ClassCastException e) {
            throw e;
        } catch (OutOfMemoryError e) {
            System.err.println("Test PASSED:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An Unexpected exception occurred:");
            throw e;
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        throw new OutOfMemoryError();
    }
}
