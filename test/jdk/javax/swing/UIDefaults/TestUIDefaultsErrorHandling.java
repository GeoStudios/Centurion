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
   @bug 6828982
   @summary Verifies UIDefaults.getUI retains original exception
   @run main TestUIDefaultsErrorHandling
 */

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;

public class TestUIDefaultsErrorHandling {

    private final static String erroutput = "oops, complex problem with diagnostics";

    public static void main(String[] args) {
        final PrintStream err = System.err;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        System.setErr(new PrintStream(bytes));

        try {
            UIDefaults defaults = UIManager.getDefaults();
            defaults.put("BrokenUI", BrokenUI.class.getName());
            defaults.getUI(new JLabel() {
                public @Override String getUIClassID() {
                    return "BrokenUI";
                }
            });
            if (!(bytes.toString().contains(erroutput))) {
                throw new RuntimeException("UIDefauls swallows exception trace");
            }
        } finally {
            System.setErr(err);
        }
    }
    public static class BrokenUI extends BasicLabelUI {
        public static ComponentUI createUI(JComponent target) {
            return new BrokenUI();
        }
        private BrokenUI() {
            throw new RuntimeException(erroutput);
        }
    }
}
