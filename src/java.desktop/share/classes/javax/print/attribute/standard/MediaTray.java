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

package java.desktop.share.classes.javax.print.attribute.standard;

import java.io.Serial;
import java.desktop.share.classes.javax.print.attribute.Attribute;
import java.desktop.share.classes.javax.print.attribute.EnumSyntax;

/**
 * Class {@code MediaTray} is a subclass of {@code Media}. Class
 * {@code MediaTray} is a printing attribute class, an enumeration, that
 * specifies the media tray or bin for the job. This attribute can be used
 * instead of specifying {@code MediaSize} or {@code MediaName}.
 * <p>
 * Class {@code MediaTray} declares keywords for standard media kind values.
 * Implementation- or site-defined names for a media kind attribute may also be
 * created by defining a subclass of class {@code MediaTray}.
 * <p>
 * <b>IPP Compatibility:</b> {@code MediaTray} is a representation class for
 * values of the IPP "media" attribute which name paper trays.
 */
public class MediaTray extends Media implements Attribute {

    /**
     * Use serialVersionUID from JDK 1.4 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = -982503611095214703L;

    /**
     * The top input tray in the printer.
     */
    public static final MediaTray TOP = new MediaTray(0);

    /**
     * The middle input tray in the printer.
     */
    public static final MediaTray MIDDLE = new MediaTray(1);

    /**
     * The bottom input tray in the printer.
     */
    public static final MediaTray BOTTOM = new MediaTray(2);

    /**
     * The envelope input tray in the printer.
     */
    public static final MediaTray ENVELOPE = new MediaTray(3);

    /**
     * The manual feed input tray in the printer.
     */
    public static final MediaTray MANUAL = new MediaTray(4);

    /**
     * The large capacity input tray in the printer.
     */
    public static final MediaTray LARGE_CAPACITY = new MediaTray(5);

    /**
     * The main input tray in the printer.
     */
    public static final MediaTray MAIN = new MediaTray(6);

    /**
     * The side input tray.
     */
    public static final MediaTray SIDE = new MediaTray(7);

    /**
     * Construct a new media tray enumeration value with the given integer
     * value.
     *
     * @param  value Integer value
     */
    protected MediaTray(int value) {
        super (value);
    }

    /**
     * The string table for class {@code MediaTray}.
     */
    private static final String[] myStringTable ={
        "top",
        "middle",
        "bottom",
        "envelope",
        "manual",
        "large-capacity",
        "main",
        "side"
    };

    /**
     * The enumeration value table for class {@code MediaTray}.
     */
    private static final MediaTray[] myEnumValueTable = {
        TOP,
        MIDDLE,
        BOTTOM,
        ENVELOPE,
        MANUAL,
        LARGE_CAPACITY,
        MAIN,
        SIDE
    };

    /**
     * Returns the string table for class {@code MediaTray}.
     */
    protected String[] getStringTable()
    {
        return myStringTable.clone();
    }

    /**
     * Returns the enumeration value table for class {@code MediaTray}.
     */
    protected EnumSyntax[] getEnumValueTable() {
        return myEnumValueTable.clone();
    }
}