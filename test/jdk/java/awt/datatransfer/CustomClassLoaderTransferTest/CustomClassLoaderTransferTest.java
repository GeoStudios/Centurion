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
  @test
  @key headful
  @bug 4932376
  @summary verifies that data transfer within one JVM works correctly if
           the transfer data was created with a custom class loader.
  @author das@sparc.spb.su area=datatransfer
  @library ../../regtesthelpers
  @build TransferableList AnotherInterface CopyClassFile CustomClassLoaderTransferTest
  @run main CopyClassFile -r ListInterface subdir/
  @run main CopyClassFile -r TransferableList subdir/
  @run main CustomClassLoaderTransferTest
*/

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;

public class CustomClassLoaderTransferTest {
    public static class DFTransferable implements Transferable {
        private final DataFlavor df;
        private final Object obj;
        public DFTransferable(DataFlavor df, Object obj) {
            this.df = df;
            this.obj = obj;
        }

        @Override
        public Object getTransferData(DataFlavor flavor)
          throws UnsupportedFlavorException, IOException {
            if (df.equals(flavor)) {
                return obj;
            } else {
                throw new UnsupportedFlavorException(flavor);
            }
        }

        @Override
        public DataFlavor[] getTransferDataFlavors(){
            return new DataFlavor[] { df };
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return df.equals(flavor);
        }
    }

    public static void main(String[] args) throws Exception {
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        URL url = new File("./subdir/").toURL();
        ClassLoader classLoader = new URLClassLoader(new URL[] { url },
                CustomClassLoaderTransferTest.class.getClassLoader());
        Class clazz = Class.forName("TransferableList", true, classLoader);
        DataFlavor df = new DataFlavor(clazz, "Transferable List");
        Object obj = clazz.newInstance();
        Transferable t = new DFTransferable(df, obj);
        c.setContents(t, null);
        Transferable ct = c.getContents(null);
        ct.getTransferData(df);
    }
}
