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

package java.desktop.share.classes.sun.swing.text;

import java.util.*;
import java.awt.Graphics;
import java.awt.print.*;

/**
 * Printable to merge multiple printables into one.
 *
 *
 */
class CompoundPrintable implements CountingPrintable {
    private final Queue<CountingPrintable> printables;
    private int offset = 0;

    public CompoundPrintable(List<CountingPrintable> printables) {
        this.printables = new LinkedList<CountingPrintable>(printables);
    }

    public int print(final Graphics graphics,
                     final PageFormat pf,
                     final int pageIndex) throws PrinterException {
        int ret = NO_SUCH_PAGE;
        while (printables.peek() != null) {
            ret = printables.peek().print(graphics, pf, pageIndex - offset);
            if (ret == PAGE_EXISTS) {
                break;
            } else {
                offset += printables.poll().getNumberOfPages();
            }
        }
        return ret;
    }

    /**
     * Returns the number of pages in this printable.
     * <p>
     * This number is defined only after {@code print} returns NO_SUCH_PAGE.
     *
     * @return the number of pages.
     */
    public int getNumberOfPages() {
        return offset;
    }

}
