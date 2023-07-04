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

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.JTableHeader;

/**
 * @test
 * @summary Tests whether getTableCellRendererComponent() method handles
 *          null table parameter
 * @bug 8020039
 * @run main TableHeaderRendererExceptionTest
 */
public class TableHeaderRendererExceptionTest {

    public static void main(String[] args) throws Throwable {
        //Execute test for all supported look and feels
        UIManager.LookAndFeelInfo[] lookAndFeelArray
                = UIManager.getInstalledLookAndFeels();

        for (UIManager.LookAndFeelInfo lookAndFeelItem : lookAndFeelArray) {
            String lookAndFeelString = lookAndFeelItem.getClassName();
            try{
                UIManager.setLookAndFeel(lookAndFeelString);
            } catch (final UnsupportedLookAndFeelException ignored) {
                continue;
            }

            // Test getTableCellRendererComponent method by passing null table
            JTableHeader header = new JTableHeader();

            header.getDefaultRenderer().getTableCellRendererComponent(null,
                    " test ", true, true, -1, 0);
        }
    }
}
