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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.dom;

import java.text.Collator;
import java.util.Locale;

import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;

/**
 * Class for carrying settings that are to be used for a particular set
 * of <code>xsl:sort</code> elements.
 */
final class SortSettings {
    /**
     * A reference to the translet object for the transformation.
     */
    private final AbstractTranslet _translet;

    /**
     * The sort order (ascending or descending) for each level of
     * <code>xsl:sort</code>
     */
    private final int[] _sortOrders;

    /**
     * The type of comparison (text or number) for each level of
     * <code>xsl:sort</code>
     */
    private final int[] _types;

    /**
     * The Locale for each level of <code>xsl:sort</code>, based on any lang
     * attribute or the default Locale.
     */
    private final Locale[] _locales;

    /**
     * The Collator object in effect for each level of <code>xsl:sort</code>
     */
    private final Collator[] _collators;

    /**
     * Case ordering for each level of <code>xsl:sort</code>.
     */
    private final String[] _caseOrders;

    /**
     * Create an instance of <code>SortSettings</code>.
     * @param translet {@link com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet}
     *                 object for the transformation
     * @param sortOrders an array specifying the sort order for each sort level
     * @param types an array specifying the type of comparison for each sort
     *              level (text or number)
     * @param locales an array specifying the Locale for each sort level
     * @param collators an array specifying the Collation in effect for each
     *                  sort level
     * @param caseOrders an array specifying whether upper-case, lower-case
     *                   or neither is to take precedence for each sort level.
     *                   The value of each element is equal to one of
     *                   <code>"upper-first", "lower-first", or ""</code>.
     */
    SortSettings(AbstractTranslet translet, int[] sortOrders, int[] types,
                 Locale[] locales, Collator[] collators, String[] caseOrders) {
        _translet = translet;
        _sortOrders = sortOrders;
        _types = types;
        _locales = locales;
        _collators = collators;
        _caseOrders = caseOrders;
    }

    /**
     * @return A reference to the translet object for the transformation.
     */
    AbstractTranslet getTranslet() {
        return _translet;
    }

    /**
     * @return An array containing the sort order (ascending or descending)
     *         for each level of <code>xsl:sort</code>
     */
    int[] getSortOrders() {
        return _sortOrders;
    }

    /**
     * @return An array containing the type of comparison (text or number)
     *         to perform for each level of <code>xsl:sort</code>
     */
    int[] getTypes() {
        return _types;
    }

    /**
     * @return An array containing the Locale object in effect for each level
     *         of <code>xsl:sort</code>
     */
    Locale[] getLocales() {
        return _locales;
    }

    /**
     * @return An array containing the Collator object in effect for each level
     *         of <code>xsl:sort</code>
     */
    Collator[] getCollators() {
        return _collators;
    }

    /**
     * @return An array specifying the case ordering for each level of
     *         <code>xsl:sort</code>.
     */
    String[] getCaseOrders() {
        return _caseOrders;
    }
}
