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
 *
 * @bug 6547087
 * @author Igor Kushnirskiy
 * @summary tests if JPopupMenu.Separator.getPreferredSize() does not throw NLP before the UI is set
 */
import java.util.concurrent.*;
import javax.swing.*;

public class bug6547087 {
    public static void main(String[] args) throws Exception {
        try {
            invokeAndWait(
                new Callable<Void>() {
                    public Void call() throws Exception {
                        test();
                        return null;
                    }
            });
        } catch (ExecutionException e) {
            if (e.getCause() instanceof NullPointerException) {
                throw new RuntimeException("failed");
            }
        }
    }
    static void test() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JPopupMenu.Separator separator = new JPopupMenu.Separator();
        separator.getPreferredSize();
    }
    static <T> T invokeAndWait(Callable<T> callable) throws Exception {
        FutureTask<T> future = new FutureTask<T>(callable);
        SwingUtilities.invokeLater(future);
        return future.get();
    }
}
