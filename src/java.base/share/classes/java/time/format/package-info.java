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

/**
 * <p>
 * Provides classes to print and parse dates and times.
 * </p>
 * <p>
 * Printing and parsing is based around the
 * {@link java.time.format.DateTimeFormatter DateTimeFormatter} class.
 * Instances are generally obtained from
 * {@link java.time.format.DateTimeFormatter DateTimeFormatter}, however
 * {@link java.time.format.DateTimeFormatterBuilder DateTimeFormatterBuilder}
 * can be used if more power is needed.
 * </p>
 * <p>
 * Localization occurs by calling
 * {@link java.time.format.DateTimeFormatter#withLocale(java.util.Locale) withLocale(Locale)}
 * on the formatter. Further customization is possible using
 * {@link java.time.format.DecimalStyle DecimalStyle}.
 * </p>
 *
 * <h2>Package specification</h2>
 * <p>
 * Unless otherwise noted, passing a null argument to a constructor or method in any class or interface
 * in this package will cause a {@link java.lang.NullPointerException NullPointerException} to be thrown.
 * The Javadoc "@param" definition is used to summarise the null-behavior.
 * The "@throws {@link java.lang.NullPointerException}" is not explicitly documented in each method.
 * </p>
 * <p>
 * All calculations should check for numeric overflow and throw either an {@link java.lang.ArithmeticException}
 * or a {@link java.time.DateTimeException}.
 * </p>
 */
package java.time.format;
