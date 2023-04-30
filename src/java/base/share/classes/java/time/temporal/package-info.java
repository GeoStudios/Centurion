/java.base.share.classes.java.time.temporal
 java.base.share.classes.java.time.temporal Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.time.temporal/

/java.base.share.classes.java.time.temporaljava.base.share.classes.java.time.temporal
 java.base.share.classes.java.time.temporal <p>
 java.base.share.classes.java.time.temporal Access to date and time using fields and units, and date time adjusters.
 java.base.share.classes.java.time.temporal </p>
 java.base.share.classes.java.time.temporal <p>
 java.base.share.classes.java.time.temporal This package expands on the base package to provide additional functionality for
 java.base.share.classes.java.time.temporal more powerful use cases. Support is included for:
 java.base.share.classes.java.time.temporal </p>
 java.base.share.classes.java.time.temporal <ul>
 java.base.share.classes.java.time.temporal <li>Units of date-time, such as years, months, days and hours</li>
 java.base.share.classes.java.time.temporal <li>Fields of date-time, such as month-of-year, day-of-week or hour-of-day</li>
 java.base.share.classes.java.time.temporal <li>Date-time adjustment functions</li>
 java.base.share.classes.java.time.temporal <li>Different definitions of weeks</li>
 java.base.share.classes.java.time.temporal </ul>
 java.base.share.classes.java.time.temporal
 java.base.share.classes.java.time.temporal <h2>Fields and Units</h2>
 java.base.share.classes.java.time.temporal <p>
 java.base.share.classes.java.time.temporal Dates and times are expressed in terms of fields and units.
 java.base.share.classes.java.time.temporal A unit is used to measure an amount of time, such as years, days or minutes.
 java.base.share.classes.java.time.temporal All units implement {@link java.time.temporal.TemporalUnit}.
 java.base.share.classes.java.time.temporal The set of well known units is defined in {@link java.time.temporal.ChronoUnit}, such as {@code DAYS}.
 java.base.share.classes.java.time.temporal The unit interface is designed to allow application defined units.
 java.base.share.classes.java.time.temporal </p>
 java.base.share.classes.java.time.temporal <p>
 java.base.share.classes.java.time.temporal A field is used to express part of a larger date-time, such as year, month-of-year or second-of-minute.
 java.base.share.classes.java.time.temporal All fields implement {@link java.time.temporal.TemporalField}.
 java.base.share.classes.java.time.temporal The set of well known fields are defined in {@link java.time.temporal.ChronoField}, such as {@code HOUR_OF_DAY}.
 java.base.share.classes.java.time.temporal Additional fields are defined by {@link java.time.temporal.JulianFields}, {@link java.time.temporal.WeekFields}
 java.base.share.classes.java.time.temporal and {@link java.time.temporal.IsoFields}.
 java.base.share.classes.java.time.temporal The field interface is designed to allow application defined fields.
 java.base.share.classes.java.time.temporal </p>
 java.base.share.classes.java.time.temporal <p>
 java.base.share.classes.java.time.temporal This package provides tools that allow the units and fields of date and time to be accessed
 java.base.share.classes.java.time.temporal in a general way most suited for frameworks.
 java.base.share.classes.java.time.temporal {@link java.time.temporal.Temporal} provides the abstraction for date time types that support fields.
 java.base.share.classes.java.time.temporal Its methods support getting the value of a field, creating a new date time with the value of
 java.base.share.classes.java.time.temporal a field modified, and querying for additional information, typically used to extract the offset or time-zone.
 java.base.share.classes.java.time.temporal </p>
 java.base.share.classes.java.time.temporal <p>
 java.base.share.classes.java.time.temporal One use of fields in application code is to retrieve fields for which there is no convenience method.
 java.base.share.classes.java.time.temporal For example, getting the day-of-month is common enough that there is a method on {@code LocalDate}
 java.base.share.classes.java.time.temporal called {@code getDayOfMonth()}. However for more unusual fields it is necessary to use the field.
 java.base.share.classes.java.time.temporal For example, {@code date.get(ChronoField.ALIGNED_WEEK_OF_MONTH)}.
 java.base.share.classes.java.time.temporal The fields also provide access to the range of valid values.
 java.base.share.classes.java.time.temporal </p>
 java.base.share.classes.java.time.temporal
 java.base.share.classes.java.time.temporal <h2>Adjustment and Query</h2>
 java.base.share.classes.java.time.temporal <p>
 java.base.share.classes.java.time.temporal A key part of the date-time problem space is adjusting a date to a new, related value,
 java.base.share.classes.java.time.temporal such as the "last day of the month", or "next Wednesday".
 java.base.share.classes.java.time.temporal These are modeled as functions that adjust a base date-time.
 java.base.share.classes.java.time.temporal The functions implement {@link java.time.temporal.TemporalAdjuster} and operate on {@code Temporal}.
 java.base.share.classes.java.time.temporal A set of common functions are provided in {@link java.time.temporal.TemporalAdjusters}.
 java.base.share.classes.java.time.temporal For example, to find the first occurrence of a day-of-week after a given date, use
 java.base.share.classes.java.time.temporal {@link java.time.temporal.TemporalAdjusters#next(DayOfWeek)}, such as
 java.base.share.classes.java.time.temporal {@code date.with(next(MONDAY))}.
 java.base.share.classes.java.time.temporal Applications can also define adjusters by implementing {@link java.time.temporal.TemporalAdjuster}.
 java.base.share.classes.java.time.temporal </p>
 java.base.share.classes.java.time.temporal <p>
 java.base.share.classes.java.time.temporal The {@link java.time.temporal.TemporalAmount} interface models amounts of relative time.
 java.base.share.classes.java.time.temporal </p>
 java.base.share.classes.java.time.temporal <p>
 java.base.share.classes.java.time.temporal In addition to adjusting a date-time, an interface is provided to enable querying via
 java.base.share.classes.java.time.temporal {@link java.time.temporal.TemporalQuery}.
 java.base.share.classes.java.time.temporal The most common implementations of the query interface are method references.
 java.base.share.classes.java.time.temporal The {@code from(TemporalAccessor)} methods on major classes can all be used, such as
 java.base.share.classes.java.time.temporal {@code LocalDate::from} or {@code Month::from}.
 java.base.share.classes.java.time.temporal Further implementations are provided in {@link java.time.temporal.TemporalQueries} as static methods.
 java.base.share.classes.java.time.temporal Applications can also define queries by implementing {@link java.time.temporal.TemporalQuery}.
 java.base.share.classes.java.time.temporal </p>
 java.base.share.classes.java.time.temporal
 java.base.share.classes.java.time.temporal <h2>Weeks</h2>
 java.base.share.classes.java.time.temporal <p>
 java.base.share.classes.java.time.temporal Different locales have different definitions of the week.
 java.base.share.classes.java.time.temporal For example, in Europe the week typically starts on a Monday, while in the US it starts on a Sunday.
 java.base.share.classes.java.time.temporal The {@link java.time.temporal.WeekFields} class models this distinction.
 java.base.share.classes.java.time.temporal </p>
 java.base.share.classes.java.time.temporal <p>
 java.base.share.classes.java.time.temporal The ISO calendar system defines an additional week-based division of years.
 java.base.share.classes.java.time.temporal This defines a year based on whole Monday to Monday weeks.
 java.base.share.classes.java.time.temporal This is modeled in {@link java.time.temporal.IsoFields}.
 java.base.share.classes.java.time.temporal </p>
 java.base.share.classes.java.time.temporal
 java.base.share.classes.java.time.temporal <h2>Package specification</h2>
 java.base.share.classes.java.time.temporal <p>
 java.base.share.classes.java.time.temporal Unless otherwise noted, passing a null argument to a constructor or method in any class or interface
 java.base.share.classes.java.time.temporal in this package will cause a {@link java.lang.NullPointerException NullPointerException} to be thrown.
 java.base.share.classes.java.time.temporal The Javadoc "@param" definition is used to summarise the null-behavior.
 java.base.share.classes.java.time.temporal The "@throws {@link java.lang.NullPointerException}" is not explicitly documented in each method.
 java.base.share.classes.java.time.temporal </p>
 java.base.share.classes.java.time.temporal <p>
 java.base.share.classes.java.time.temporal All calculations should check for numeric overflow and throw either an {@link java.lang.ArithmeticException}
 java.base.share.classes.java.time.temporal or a {@link java.time.DateTimeException}.
 java.base.share.classes.java.time.temporal </p>
 java.base.share.classes.java.time.temporal @since 1.8
 java.base.share.classes.java.time.temporal/
package java.time.temporal;
