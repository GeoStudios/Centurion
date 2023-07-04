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

import java.awt.*;
import java.awt.print.PrinterJob;
import javax.print.PrintServiceLookup;

/**
 * @test
 * @bug 6870661
 * @summary Verify that no native dialog is opened for a custom PrintService
 * @run main/manual PrintDialog
 * @author reinhapa
 */
public class PrintDialog {

    private static final String instructions =
        "This test shows a non native print dialog having a 'test' print service\n" +
        "selected. No other options are selectable on the General tab. The other\n" +
        "tabs are as follows:\n" +
        "Page Setup: Media & Margins enabled, Orientation disabled\n" +
        "Appearance: All parts disabled\n\n" +
        "Test passes if the dialog is shown as described above.";

    public static void main(String[] args) throws Exception {
        // instruction dialog
        Frame instruction = new Frame("Verify that no native print dialog is showed");
        instruction.add(new TextArea(instructions));
        instruction.pack();
        instruction.show();
        // test begin
        PrintServiceStub service = new PrintServiceStub("test");
        PrintServiceLookup.registerService(service);
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintService(service);
        job.printDialog();
        System.out.println("test passed");
    }
}
