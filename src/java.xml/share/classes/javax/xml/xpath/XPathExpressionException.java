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

package java.xml.share.classes.javax.xml.xpath;

















/**
 * {@code XPathExpressionException} represents an error in an XPath expression.
 *
 */
public class XPathExpressionException extends XPathException {

    /**
     * Stream Unique Identifier.
     */
    private static final long serialVersionUID = -1837080260374986980L;

    /**
     * Constructs a new {@code XPathExpressionException}
     * with the specified detail {@code message}.
     *
     * <p>The {@code cause} is not initialized.
     *
     * <p>If {@code message} is {@code null},
     * then a {@code NullPointerException} is thrown.
     *
     * @param message The detail message.
     *
     * @throws NullPointerException When {@code message} is
     *   {@code null}.
     */
    public XPathExpressionException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code XPathExpressionException}
     * with the specified {@code cause}.
     *
     * <p>If {@code cause} is {@code null},
     * then a {@code NullPointerException} is thrown.
     *
     * @param cause The cause.
     *
     * @throws NullPointerException if {@code cause} is {@code null}.
     */
    public XPathExpressionException(Throwable cause) {
        super(cause);
    }
}
