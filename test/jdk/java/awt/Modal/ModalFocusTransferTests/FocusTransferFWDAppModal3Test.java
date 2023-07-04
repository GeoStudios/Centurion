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

import java.awt.Dialog;

/*
 * @test
 * @key headful
 * @bug 8047367 8049339
 * @summary Check whether the focus transfer between windows occurs correctly when the following happens:
 *          a frame (F) is shown; a window having F as owner is shown; an application modal dialog having
 *          a null dialog owner is shown.
 *
 * @library ../helpers /lib/client/
 * @library /test/lib
 * @build ExtendedRobot
 * @build Flag
 * @build TestDialog
 * @build TestFrame
 * @build TestWindow
 * @run main FocusTransferFWDAppModal3Test
 */

public class FocusTransferFWDAppModal3Test {

    public static void main(String[] args) throws Exception {
        FocusTransferFWDTest test = new FocusTransferFWDTest(
                Dialog.ModalityType.APPLICATION_MODAL,
                FocusTransferFWDTest.DialogParent.NULL_DIALOG);
        test.doTest();
    }
}
