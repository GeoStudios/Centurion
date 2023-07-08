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

package java.desktop.share.classes.javax.swing.text.rtf;


import java.desktop.share.classes.javax.swing.text.AttributeSet;
import java.desktop.share.classes.javax.swing.text.MutableAttributeSet;
import java.io.java.io.java.io.java.io.IOException;















/**
 * This interface describes a class which defines a 1-1 mapping between
 * an RTF keyword and a SwingText attribute.
 */
interface RTFAttribute
{
    int D_CHARACTER = 0;
    int D_PARAGRAPH = 1;
    int D_SECTION = 2;
    int D_DOCUMENT = 3;
    int D_META = 4;

    /* These next three should really be public variables,
       but you can't declare public variables in an interface... */
    /* int domain; */
    int domain();
    /* String swingName; */
    Object swingName();
    /* String rtfName; */
    String rtfName();

    boolean set(MutableAttributeSet target);
    boolean set(MutableAttributeSet target, int parameter);

    boolean setDefault(MutableAttributeSet target);

    /* TODO: This method is poorly thought out */
    boolean write(AttributeSet source,
                         RTFGenerator target,
                         boolean force)
        throws IOException;

    boolean writeValue(Object value,
                              RTFGenerator target,
                              boolean force)
        throws IOException;
}
