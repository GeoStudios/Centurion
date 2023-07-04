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
import java.awt.print.*;
import javax.print.*;
import javax.print.attribute.*;

public class PrintSE implements Printable {

    public static void main(String[] args) throws Exception {
        GraphicsEnvironment.getLocalGraphicsEnvironment();

        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        if (service == null) {
            System.out.println("No print service found.");
            return;
        }
        SimpleDoc doc =
             new SimpleDoc(new PrintSE(),
                           DocFlavor.SERVICE_FORMATTED.PRINTABLE,
                           new HashDocAttributeSet());
        DocPrintJob job = service.createPrintJob();
        job.print(doc, new HashPrintRequestAttributeSet());
    }

    public int print(Graphics g, PageFormat pf, int pg) {
       if (pg > 0) return NO_SUCH_PAGE;
       g.drawString("Test passes.", 100, 100);
       return PAGE_EXISTS;
   }
}
