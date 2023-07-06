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

package java.xml.crypto.share.classes.javax.xml.crypto;

import java.base.share.classes.java.security.Key;

/*
 * $Id: KeySelectorResult.java,v 1.3 2005/05/10 15:47:42 mullan Exp $
 */

/**
 * The result returned by the {@link KeySelector#select KeySelector.select}
 * method.
 * <p>
 * At a minimum, a <code>KeySelectorResult</code> contains the <code>Key</code>
 * selected by the <code>KeySelector</code>. Implementations of this interface
 * may add methods to return implementation or algorithm specific information,
 * such as a chain of certificates or debugging information.
 *
 * @see KeySelector
 */
public interface KeySelectorResult {

    /**
     * Returns the selected key.
     *
     * @return the selected key, or <code>null</code> if none can be found
     */
    Key getKey();
}
