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
 * @bug 8047179 8044429
 * @summary Check whether a FileDialog blocks an application modality excluded Frame
 *          (it shouldn't). Checks also whether setting a parent frame to be
 *          modality excluded excludes its children from being blocked too.
 *
 * @library ../helpers /lib/client/
 * @library /test/lib
 * @build ExtendedRobot
 * @build Flag
 * @build TestDialog
 * @build TestFrame
 * @build TestWindow
 * @run main ApplicationExcludeFrameFileTest
 */

public class ApplicationExcludeFrameFileTest {

    public static void main(String[] args) throws Exception {
        ExcludeFrameTest test = new ExcludeFrameTest(
                Dialog.ModalExclusionType.APPLICATION_EXCLUDE,
                ExcludeFrameTest.DialogToShow.FILE_DIALOG);
        test.doTest();
    }
}
