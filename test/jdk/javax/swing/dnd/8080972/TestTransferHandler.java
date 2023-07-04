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
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import java.awt.datatransfer.*;
/*
 * @test
 * @bug 8080972
 * @run main/othervm -Djava.security.manager=allow TestTransferHandler
 * @summary Audit Core Reflection in module java.desktop for places that will
 *          require changes to work with modules
 * @author Alexander Scherbatiy
 */

public class TestTransferHandler {

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(TestTransferHandler::testTransferHandler);
        System.setSecurityManager(new SecurityManager());
        SwingUtilities.invokeAndWait(TestTransferHandler::testTransferHandler);
    }

    private static void testTransferHandler() {
        try {
            TransferHandler transferHandler = new TransferHandler("userColor");
            UserJComponent comp = new UserJComponent();

            final String colorType = DataFlavor.javaJVMLocalObjectMimeType
                    + ";class=java.awt.Color";
            final DataFlavor colorFlavor = new DataFlavor(colorType);

            Transferable transferable = new Transferable() {

                public DataFlavor[] getTransferDataFlavors() {
                    return new DataFlavor[]{colorFlavor};

                }

                public boolean isDataFlavorSupported(DataFlavor flavor) {
                    return true;
                }

                public Object getTransferData(DataFlavor flavor) {
                    return UserJComponent.TEST_COLOR;
                }

            };

            transferHandler.importData(comp, transferable);

            if (!UserJComponent.TEST_COLOR.equals(comp.color)) {
                throw new RuntimeException("Wrong color!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static class UserJComponent extends JComponent {

        public static final Color USER_COLOR = new Color(10, 20, 30);
        public static final Color TEST_COLOR = new Color(15, 25, 35);

        Color color = USER_COLOR;

        public UserJComponent() {

        }

        public Color getUserColor() {
            return color;
        }

        public void setUserColor(Color color) {
            this.color = color;
        }
    }
}
