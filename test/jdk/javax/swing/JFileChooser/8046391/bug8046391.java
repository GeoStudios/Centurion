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
 * @bug 8046391
 * @requires (os.family == "windows")
 * @summary JFileChooser hangs if displayed in Windows L&F
 * @author Alexey Ivanov
 * @library /test/lib
 * @modules java.desktop/com.sun.java.swing.plaf.windows
 * @build jdk.test.lib.Platform
 * @run main/othervm/timeout=10 bug8046391
*/

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import jdk.test.lib.Platform;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class bug8046391  {

    public static void main(String[] args) throws Exception {
        if (!Platform.isWindows()) {
            System.out.println("This test is for Windows only... skipping!");
            return;
        }

        SwingUtilities.invokeAndWait(() -> {
            try {
                UIManager.setLookAndFeel(new WindowsLookAndFeel());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            System.out.println("Creating JFileChooser...");
            JFileChooser fileChooser = new JFileChooser();
            System.out.println("Test passed: chooser = " + fileChooser);
        });
        // Test fails if creating JFileChooser hangs
    }

}
