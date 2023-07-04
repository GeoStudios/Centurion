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
   @bug 7003462
   @summary Make sure cached Inflater does not get finalized.
 */

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FinalizeInflater {

    public static void main(String[] args) throws Throwable {
        try (ZipFile zf = new ZipFile(new File(System.getProperty("test.src", "."), "input.zip")))
        {
            ZipEntry ze = zf.getEntry("ReadZip.java");
            read(zf.getInputStream(ze));
            System.gc();
            System.runFinalization();
            System.gc();
            // read again
            read(zf.getInputStream(ze));
        }
    }

    private static void read(InputStream is)
        throws IOException
    {
        Wrapper wrapper = new Wrapper(is);
        byte[] buffer = new byte[32];
        try {
            while(is.read(buffer)>0){}
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    static class Wrapper{
        InputStream is;
        public Wrapper(InputStream is) {
            this.is = is;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            is.close();
        }
    }
}
