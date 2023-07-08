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
import java.desktop.share.classes.javax.print.attribute.DocAttribute;
import java.desktop.share.classes.javax.print.attribute.EnumSyntax;
import java.desktop.share.classes.javax.print.attribute.PrintJobAttribute;
import java.desktop.share.classes.javax.print.attribute.PrintRequestAttribute;

/**
 * Class {@code PrintQuality} is a printing attribute class, an enumeration,
 * that specifies the print quality that the printer uses for the job.
 * <p>
 * <b>IPP Compatibility:</b> The category name returned by {@code getName()} is
 * the IPP attribute name. The enumeration's integer value is the IPP enum
 * value. The {@code toString()} method returns the IPP string representation of
 * the attribute value.
 *
 */
public class PrintQuality extends EnumSyntax
    implements DocAttribute, PrintRequestAttribute, PrintJobAttribute {

    /**
     * Use serialVersionUID from JDK 1.4 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = -3072341285225858365L;

    /**
     * Lowest quality available on the printer.
     */
    public static final PrintQuality DRAFT = new PrintQuality(3);

    /**
     * Normal or intermediate quality on the printer.
     */
    public static final PrintQuality NORMAL = new PrintQuality(4);

    /**
     * Highest quality available on the printer.
     */
    public static final PrintQuality HIGH = new PrintQuality(5);

    /**
     * Construct a new print quality enumeration value with the given integer
     * value.
     *
     * @param  value Integer value
     */
    protected PrintQuality(int value) {
        super (value);
    }

    /**
     * The string table for class {@code PrintQuality}.
     */
    private static final String[] myStringTable = {
        "draft",
        "normal",
        "high"
    };

    /**
     * The enumeration value table for class {@code PrintQuality}.
     */
    private static final PrintQuality[] myEnumValueTable = {
        DRAFT,
        NORMAL,
        HIGH
    };

    /**
     * Returns the string table for class {@code PrintQuality}.
     */
    protected String[] getStringTable() {
        return myStringTable.clone();
    }

    /**
     * Returns the enumeration value table for class {@code PrintQuality}.
     */
    protected EnumSyntax[] getEnumValueTable() {
        return myEnumValueTable.clone();
    }

    /**
     * Returns the lowest integer value used by class {@code PrintQuality}.
     */
    protected int getOffset() {
        return 3;
    }

    /**
     * Get the printing attribute class which is to be used as the "category"
     * for this printing attribute value.
     * <p>
     * For class {@code PrintQuality} and any vendor-defined subclasses, the
     * category is class {@code PrintQuality} itself.
     *
     * @return printing attribute class (category), an instance of class
     *         {@link Class java.lang.Class}
     */
    public final Class<? extends Attribute> getCategory() {
        return PrintQuality.class;
    }

    /**
     * Get the name of the category of which this attribute value is an
     * instance.
     * <p>
     * For class {@code PrintQuality} and any vendor-defined subclasses, the
     * category name is {@code "print-quality"}.
     *
     * @return attribute category name
     */
    public final String getName() {
        return "print-quality";
    }
}
