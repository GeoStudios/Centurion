/java.base.share.classes.java.time
 java.base.share.classes.java.time Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.time/

/java.base.share.classes.java.timejava.base.share.classes.java.time
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time The main API for dates, times, instants, and durations.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time The classes defined here represent the principle date-time concepts,
 java.base.share.classes.java.time including instants, durations, dates, times, time-zones and periods.
 java.base.share.classes.java.time They are based on the ISO calendar system, which is the <i>de facto</i> world
 java.base.share.classes.java.time calendar following the proleptic Gregorian rules.
 java.base.share.classes.java.time All the classes are immutable and thread-safe.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time Each date time instance is composed of fields that are conveniently
 java.base.share.classes.java.time made available by the APIs.  For lower level access to the fields refer
 java.base.share.classes.java.time to the {@code java.time.temporal} package.
 java.base.share.classes.java.time Each class includes support for printing and parsing all manner of dates and times.
 java.base.share.classes.java.time Refer to the {@code java.time.format} package for customization options.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time The {@code java.time.chrono} package contains the calendar neutral API
 java.base.share.classes.java.time {@link java.time.chrono.ChronoLocalDate ChronoLocalDate},
 java.base.share.classes.java.time {@link java.time.chrono.ChronoLocalDateTime ChronoLocalDateTime},
 java.base.share.classes.java.time {@link java.time.chrono.ChronoZonedDateTime ChronoZonedDateTime} and
 java.base.share.classes.java.time {@link java.time.chrono.Era Era}.
 java.base.share.classes.java.time This is intended for use by applications that need to use localized calendars.
 java.base.share.classes.java.time It is recommended that applications use the ISO-8601 date and time classes from
 java.base.share.classes.java.time this package across system boundaries, such as to the database or across the network.
 java.base.share.classes.java.time The calendar neutral API should be reserved for interactions with users.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time
 java.base.share.classes.java.time <h2>Dates and Times</h2>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time {@link java.time.Instant} is essentially a numeric timestamp.
 java.base.share.classes.java.time The current Instant can be retrieved from a {@link java.time.Clock}.
 java.base.share.classes.java.time This is useful for logging and persistence of a point in time
 java.base.share.classes.java.time and has in the past been associated with storing the result
 java.base.share.classes.java.time from {@link java.lang.System#currentTimeMillis()}.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time {@link java.time.LocalDate} stores a date without a time.
 java.base.share.classes.java.time This stores a date like '2010-12-03' and could be used to store a birthday.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time {@link java.time.LocalTime} stores a time without a date.
 java.base.share.classes.java.time This stores a time like '11:30' and could be used to store an opening or closing time.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time {@link java.time.LocalDateTime} stores a date and time.
 java.base.share.classes.java.time This stores a date-time like '2010-12-03T11:30'.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time {@link java.time.ZonedDateTime} stores a date and time with a time-zone.
 java.base.share.classes.java.time This is useful if you want to perform accurate calculations of
 java.base.share.classes.java.time dates and times taking into account the {@link java.time.ZoneId}, such as 'Europe/Paris'.
 java.base.share.classes.java.time Where possible, it is recommended to use a simpler class without a time-zone.
 java.base.share.classes.java.time The widespread use of time-zones tends to add considerable complexity to an application.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time
 java.base.share.classes.java.time <h2>Duration and Period</h2>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time Beyond dates and times, the API also allows the storage of periods and durations of time.
 java.base.share.classes.java.time A {@link java.time.Duration} is a simple measure of time along the time-line in nanoseconds.
 java.base.share.classes.java.time A {@link java.time.Period} expresses an amount of time in units meaningful
 java.base.share.classes.java.time to humans, such as years or days.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time
 java.base.share.classes.java.time <h2>Additional value types</h2>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time {@link java.time.Month} stores a month on its own.
 java.base.share.classes.java.time This stores a single month-of-year in isolation, such as 'DECEMBER'.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time {@link java.time.DayOfWeek} stores a day-of-week on its own.
 java.base.share.classes.java.time This stores a single day-of-week in isolation, such as 'TUESDAY'.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time {@link java.time.Year} stores a year on its own.
 java.base.share.classes.java.time This stores a single year in isolation, such as '2010'.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time {@link java.time.YearMonth} stores a year and month without a day or time.
 java.base.share.classes.java.time This stores a year and month, such as '2010-12' and could be used for a credit card expiry.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time {@link java.time.MonthDay} stores a month and day without a year or time.
 java.base.share.classes.java.time This stores a month and day-of-month, such as '--12-03' and
 java.base.share.classes.java.time could be used to store an annual event like a birthday without storing the year.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time {@link java.time.OffsetTime} stores a time and offset from UTC without a date.
 java.base.share.classes.java.time This stores a date like '11:30+01:00'.
 java.base.share.classes.java.time The {@link java.time.ZoneOffset ZoneOffset} is of the form '+01:00'.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time {@link java.time.OffsetDateTime} stores a date and time and offset from UTC.
 java.base.share.classes.java.time This stores a date-time like '2010-12-03T11:30+01:00'.
 java.base.share.classes.java.time This is sometimes found in XML messages and other forms of persistence,
 java.base.share.classes.java.time but contains less information than a full time-zone.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time
 java.base.share.classes.java.time <h2>Package specification</h2>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time Unless otherwise noted, passing a null argument to a constructor or method in any class or interface
 java.base.share.classes.java.time in this package will cause a {@link java.lang.NullPointerException NullPointerException} to be thrown.
 java.base.share.classes.java.time The Javadoc "@param" definition is used to summarise the null-behavior.
 java.base.share.classes.java.time The "@throws {@link java.lang.NullPointerException}" is not explicitly documented in each method.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time All calculations should check for numeric overflow and throw either an {@link java.lang.ArithmeticException}
 java.base.share.classes.java.time or a {@link java.time.DateTimeException}.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time
 java.base.share.classes.java.time <h2>Design notes (non normative)</h2>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time The API has been designed to reject null early and to be clear about this behavior.
 java.base.share.classes.java.time A key exception is any method that takes an object and returns a boolean, for the purpose
 java.base.share.classes.java.time of checking or validating, will generally return false for null.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time The API is designed to be type-safe where reasonable in the main high-level API.
 java.base.share.classes.java.time Thus, there are separate classes for the distinct concepts of date, time and date-time,
 java.base.share.classes.java.time plus variants for offset and time-zone.
 java.base.share.classes.java.time This can seem like a lot of classes, but most applications can begin with just five date/time types.
 java.base.share.classes.java.time <ul>
 java.base.share.classes.java.time <li>{@link java.time.Instant} - a timestamp</li>
 java.base.share.classes.java.time <li>{@link java.time.LocalDate} - a date without a time, or any reference to an offset or time-zone</li>
 java.base.share.classes.java.time <li>{@link java.time.LocalTime} - a time without a date, or any reference to an offset or time-zone</li>
 java.base.share.classes.java.time <li>{@link java.time.LocalDateTime} - combines date and time, but still without any offset or time-zone</li>
 java.base.share.classes.java.time <li>{@link java.time.ZonedDateTime} - a "full" date-time with time-zone and resolved offset from UTC/Greenwich</li>
 java.base.share.classes.java.time </ul>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time {@code Instant} is the closest equivalent class to {@code java.util.Date}.
 java.base.share.classes.java.time {@code ZonedDateTime} is the closest equivalent class to {@code java.util.GregorianCalendar}.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time Where possible, applications should use {@code LocalDate}, {@code LocalTime} and {@code LocalDateTime}
 java.base.share.classes.java.time to better model the domain. For example, a birthday should be stored in a code {@code LocalDate}.
 java.base.share.classes.java.time Bear in mind that any use of a {@linkplain java.time.ZoneId time-zone}, such as 'Europe/Paris', adds
 java.base.share.classes.java.time considerable complexity to a calculation.
 java.base.share.classes.java.time Many applications can be written only using {@code LocalDate}, {@code LocalTime} and {@code Instant},
 java.base.share.classes.java.time with the time-zone added at the user interface (UI) layer.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time The offset-based date-time types {@code OffsetTime} and {@code OffsetDateTime},
 java.base.share.classes.java.time are intended primarily for use with network protocols and database access.
 java.base.share.classes.java.time For example, most databases cannot automatically store a time-zone like 'Europe/Paris', but
 java.base.share.classes.java.time they can store an offset like '+02:00'.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time Classes are also provided for the most important sub-parts of a date, including {@code Month},
 java.base.share.classes.java.time {@code DayOfWeek}, {@code Year}, {@code YearMonth} and {@code MonthDay}.
 java.base.share.classes.java.time These can be used to model more complex date-time concepts.
 java.base.share.classes.java.time For example, {@code YearMonth} is useful for representing a credit card expiry.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time Note that while there are a large number of classes representing different aspects of dates,
 java.base.share.classes.java.time there are relatively few dealing with different aspects of time.
 java.base.share.classes.java.time Following type-safety to its logical conclusion would have resulted in classes for
 java.base.share.classes.java.time hour-minute, hour-minute-second and hour-minute-second-nanosecond.
 java.base.share.classes.java.time While logically pure, this was not a practical option as it would have almost tripled the
 java.base.share.classes.java.time number of classes due to the combinations of date and time.
 java.base.share.classes.java.time Thus, {@code LocalTime} is used for all precisions of time, with zeroes used to imply lower precision.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time Following full type-safety to its ultimate conclusion might also argue for a separate class
 java.base.share.classes.java.time for each field in date-time, such as a class for HourOfDay and another for DayOfMonth.
 java.base.share.classes.java.time This approach was tried, but was excessively complicated in the Java language, lacking usability.
 java.base.share.classes.java.time A similar problem occurs with periods.
 java.base.share.classes.java.time There is a case for a separate class for each period unit, such as a type for Years and a type for Minutes.
 java.base.share.classes.java.time However, this yields a lot of classes and a problem of type conversion.
 java.base.share.classes.java.time Thus, the set of date-time types provided is a compromise between purity and practicality.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time The API has a relatively large surface area in terms of number of methods.
 java.base.share.classes.java.time This is made manageable through the use of consistent method prefixes.
 java.base.share.classes.java.time <ul>
 java.base.share.classes.java.time <li>{@code of} - static factory method</li>
 java.base.share.classes.java.time <li>{@code parse} - static factory method focused on parsing</li>
 java.base.share.classes.java.time <li>{@code get} - gets the value of something</li>
 java.base.share.classes.java.time <li>{@code is} - checks if something is true</li>
 java.base.share.classes.java.time <li>{@code with} - the immutable equivalent of a setter</li>
 java.base.share.classes.java.time <li>{@code plus} - adds an amount to an object</li>
 java.base.share.classes.java.time <li>{@code minus} - subtracts an amount from an object</li>
 java.base.share.classes.java.time <li>{@code to} - converts this object to another type</li>
 java.base.share.classes.java.time <li>{@code at} - combines this object with another, such as {@code date.atTime(time)}</li>
 java.base.share.classes.java.time </ul>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time Multiple calendar systems is an awkward addition to the design challenges.
 java.base.share.classes.java.time The first principle is that most users want the standard ISO calendar system.
 java.base.share.classes.java.time As such, the main classes are ISO-only. The second principle is that most of those that want a
 java.base.share.classes.java.time non-ISO calendar system want it for user interaction, thus it is a UI localization issue.
 java.base.share.classes.java.time As such, date and time objects should be held as ISO objects in the data model and persistent
 java.base.share.classes.java.time storage, only being converted to and from a local calendar for display.
 java.base.share.classes.java.time The calendar system would be stored separately in the user preferences.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time There are, however, some limited use cases where users believe they need to store and use
 java.base.share.classes.java.time dates in arbitrary calendar systems throughout the application.
 java.base.share.classes.java.time This is supported by {@link java.time.chrono.ChronoLocalDate}, however it is vital to read
 java.base.share.classes.java.time all the associated warnings in the Javadoc of that interface before using it.
 java.base.share.classes.java.time In summary, applications that require general interoperation between multiple calendar systems
 java.base.share.classes.java.time typically need to be written in a very different way to those only using the ISO calendar,
 java.base.share.classes.java.time thus most applications should just use ISO and avoid {@code ChronoLocalDate}.
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <p>
 java.base.share.classes.java.time The API is also designed for user extensibility, as there are many ways of calculating time.
 java.base.share.classes.java.time The {@linkplain java.time.temporal.TemporalField field} and {@linkplain java.time.temporal.TemporalUnit unit}
 java.base.share.classes.java.time API, accessed via {@link java.time.temporal.TemporalAccessor TemporalAccessor} and
 java.base.share.classes.java.time {@link java.time.temporal.Temporal Temporal} provide considerable flexibility to applications.
 java.base.share.classes.java.time In addition, the {@link java.time.temporal.TemporalQuery TemporalQuery} and
 java.base.share.classes.java.time {@link java.time.temporal.TemporalAdjuster TemporalAdjuster} interfaces provide day-to-day
 java.base.share.classes.java.time power, allowing code to read close to business requirements:
 java.base.share.classes.java.time </p>
 java.base.share.classes.java.time <pre>
 java.base.share.classes.java.time   LocalDate customerBirthday = customer.loadBirthdayFromDatabase();
 java.base.share.classes.java.time   LocalDate today = LocalDate.now();
 java.base.share.classes.java.time   if (customerBirthday.equals(today)) {
 java.base.share.classes.java.time     LocalDate specialOfferExpiryDate = today.plusWeeks(2).with(next(FRIDAY));
 java.base.share.classes.java.time     customer.sendBirthdaySpecialOffer(specialOfferExpiryDate);
 java.base.share.classes.java.time   }
 java.base.share.classes.java.time
 java.base.share.classes.java.time </pre>
 java.base.share.classes.java.time
 java.base.share.classes.java.time @since 1.8
 java.base.share.classes.java.time/
package java.time;
