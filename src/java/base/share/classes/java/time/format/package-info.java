/java.base.share.classes.java.time.format
 java.base.share.classes.java.time.format Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.time.format/

/java.base.share.classes.java.time.formatjava.base.share.classes.java.time.format
 java.base.share.classes.java.time.format <p>
 java.base.share.classes.java.time.format Provides classes to print and parse dates and times.
 java.base.share.classes.java.time.format </p>
 java.base.share.classes.java.time.format <p>
 java.base.share.classes.java.time.format Printing and parsing is based around the
 java.base.share.classes.java.time.format {@link java.time.format.DateTimeFormatter DateTimeFormatter} class.
 java.base.share.classes.java.time.format Instances are generally obtained from
 java.base.share.classes.java.time.format {@link java.time.format.DateTimeFormatter DateTimeFormatter}, however
 java.base.share.classes.java.time.format {@link java.time.format.DateTimeFormatterBuilder DateTimeFormatterBuilder}
 java.base.share.classes.java.time.format can be used if more power is needed.
 java.base.share.classes.java.time.format </p>
 java.base.share.classes.java.time.format <p>
 java.base.share.classes.java.time.format Localization occurs by calling
 java.base.share.classes.java.time.format {@link java.time.format.DateTimeFormatter#withLocale(java.util.Locale) withLocale(Locale)}
 java.base.share.classes.java.time.format on the formatter. Further customization is possible using
 java.base.share.classes.java.time.format {@link java.time.format.DecimalStyle DecimalStyle}.
 java.base.share.classes.java.time.format </p>
 java.base.share.classes.java.time.format
 java.base.share.classes.java.time.format <h2>Package specification</h2>
 java.base.share.classes.java.time.format <p>
 java.base.share.classes.java.time.format Unless otherwise noted, passing a null argument to a constructor or method in any class or interface
 java.base.share.classes.java.time.format in this package will cause a {@link java.lang.NullPointerException NullPointerException} to be thrown.
 java.base.share.classes.java.time.format The Javadoc "@param" definition is used to summarise the null-behavior.
 java.base.share.classes.java.time.format The "@throws {@link java.lang.NullPointerException}" is not explicitly documented in each method.
 java.base.share.classes.java.time.format </p>
 java.base.share.classes.java.time.format <p>
 java.base.share.classes.java.time.format All calculations should check for numeric overflow and throw either an {@link java.lang.ArithmeticException}
 java.base.share.classes.java.time.format or a {@link java.time.DateTimeException}.
 java.base.share.classes.java.time.format </p>
 java.base.share.classes.java.time.format @since 1.8
 java.base.share.classes.java.time.format/
package java.time.format;
