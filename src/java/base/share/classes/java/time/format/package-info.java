/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
 * @since 1.8
 */
package java.time.format;
