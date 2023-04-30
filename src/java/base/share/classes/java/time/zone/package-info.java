/java.base.share.classes.java.time.zone
 java.base.share.classes.java.time.zone Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.time.zone/

/java.base.share.classes.java.time.zonejava.base.share.classes.java.time.zone
 java.base.share.classes.java.time.zone <p>
 java.base.share.classes.java.time.zone Support for time-zones and their rules.
 java.base.share.classes.java.time.zone </p>
 java.base.share.classes.java.time.zone <p>
 java.base.share.classes.java.time.zone Daylight Saving Time and Time-Zones are concepts used by Governments to alter local time.
 java.base.share.classes.java.time.zone This package provides support for time-zones, their rules and the resulting
 java.base.share.classes.java.time.zone gaps and overlaps in the local time-line typically caused by Daylight Saving Time.
 java.base.share.classes.java.time.zone </p>
 java.base.share.classes.java.time.zone
 java.base.share.classes.java.time.zone <h2>Package specification</h2>
 java.base.share.classes.java.time.zone <p>
 java.base.share.classes.java.time.zone Unless otherwise noted, passing a null argument to a constructor or method in any class or interface
 java.base.share.classes.java.time.zone in this package will cause a {@link java.lang.NullPointerException NullPointerException} to be thrown.
 java.base.share.classes.java.time.zone The Javadoc "@param" definition is used to summarise the null-behavior.
 java.base.share.classes.java.time.zone The "@throws {@link java.lang.NullPointerException}" is not explicitly documented in each method.
 java.base.share.classes.java.time.zone </p>
 java.base.share.classes.java.time.zone <p>
 java.base.share.classes.java.time.zone All calculations should check for numeric overflow and throw either an {@link java.lang.ArithmeticException}
 java.base.share.classes.java.time.zone or a {@link java.time.DateTimeException}.
 java.base.share.classes.java.time.zone </p>
 java.base.share.classes.java.time.zone @since 1.8
 java.base.share.classes.java.time.zone/
package java.time.zone;
