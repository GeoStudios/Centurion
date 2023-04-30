/java.base.share.classes.java.time.chrono
 java.base.share.classes.java.time.chrono Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 java.base.share.classes.java.time.chrono/

/java.base.share.classes.java.time.chronojava.base.share.classes.java.time.chrono
 java.base.share.classes.java.time.chrono <p>
 java.base.share.classes.java.time.chrono Generic API for calendar systems other than the default ISO.
 java.base.share.classes.java.time.chrono </p>
 java.base.share.classes.java.time.chrono <p>
 java.base.share.classes.java.time.chrono The main API is based around the calendar system defined in ISO-8601.
 java.base.share.classes.java.time.chrono However, there are other calendar systems, and this package provides basic support for them.
 java.base.share.classes.java.time.chrono The alternate calendars are provided in the {@link java.time.chrono} package.
 java.base.share.classes.java.time.chrono </p>
 java.base.share.classes.java.time.chrono <p>
 java.base.share.classes.java.time.chrono A calendar system is defined by the {@link java.time.chrono.Chronology} interface,
 java.base.share.classes.java.time.chrono while a date in a calendar system is defined by the {@link java.time.chrono.ChronoLocalDate} interface.
 java.base.share.classes.java.time.chrono </p>
 java.base.share.classes.java.time.chrono <p>
 java.base.share.classes.java.time.chrono It is intended that applications use the main API whenever possible, including code to read and write
 java.base.share.classes.java.time.chrono from a persistent data store, such as a database, and to send dates and times across a network.
 java.base.share.classes.java.time.chrono The "chrono" classes are then used at the user interface level to deal with localized input/output.
 java.base.share.classes.java.time.chrono See {@link java.time.chrono.ChronoLocalDate ChronoLocalDate}
 java.base.share.classes.java.time.chrono for a full discussion of the issues.
 java.base.share.classes.java.time.chrono </p>
 java.base.share.classes.java.time.chrono <p>
 java.base.share.classes.java.time.chrono Using non-ISO calendar systems in an application introduces significant extra complexity.
 java.base.share.classes.java.time.chrono Ensure that the warnings and recommendations in {@code ChronoLocalDate} have been read before
 java.base.share.classes.java.time.chrono working with the "chrono" interfaces.
 java.base.share.classes.java.time.chrono </p>
 java.base.share.classes.java.time.chrono <p>
 java.base.share.classes.java.time.chrono The supported calendar systems includes:
 java.base.share.classes.java.time.chrono </p>
 java.base.share.classes.java.time.chrono <ul>
 java.base.share.classes.java.time.chrono <li>{@link java.time.chrono.HijrahChronology Hijrah calendar}</li>
 java.base.share.classes.java.time.chrono <li>{@link java.time.chrono.JapaneseChronology Japanese calendar}</li>
 java.base.share.classes.java.time.chrono <li>{@link java.time.chrono.MinguoChronology Minguo calendar}</li>
 java.base.share.classes.java.time.chrono <li>{@link java.time.chrono.ThaiBuddhistChronology Thai Buddhist calendar}</li>
 java.base.share.classes.java.time.chrono </ul>
 java.base.share.classes.java.time.chrono
 java.base.share.classes.java.time.chrono <h2>Example</h2>
 java.base.share.classes.java.time.chrono <p>
 java.base.share.classes.java.time.chrono This example lists today's date for all of the available calendars.
 java.base.share.classes.java.time.chrono </p>
 java.base.share.classes.java.time.chrono <pre>
 java.base.share.classes.java.time.chrono   // Enumerate the list of available calendars and print today's date for each.
 java.base.share.classes.java.time.chrono       Set&lt;Chronology&gt; chronos = Chronology.getAvailableChronologies();
 java.base.share.classes.java.time.chrono       for (Chronology chrono : chronos) {
 java.base.share.classes.java.time.chrono           ChronoLocalDate date = chrono.dateNow();
 java.base.share.classes.java.time.chrono           System.out.printf("   %20s: %s%n", chrono.getId(), date.toString());
 java.base.share.classes.java.time.chrono       }
 java.base.share.classes.java.time.chrono </pre>
 java.base.share.classes.java.time.chrono
 java.base.share.classes.java.time.chrono <p>
 java.base.share.classes.java.time.chrono This example creates and uses a date in a named non-ISO calendar system.
 java.base.share.classes.java.time.chrono </p>
 java.base.share.classes.java.time.chrono <pre>
 java.base.share.classes.java.time.chrono   // Print the Thai Buddhist date
 java.base.share.classes.java.time.chrono       ChronoLocalDate now1 = Chronology.of("ThaiBuddhist").dateNow();
 java.base.share.classes.java.time.chrono       int day = now1.get(ChronoField.DAY_OF_MONTH);
 java.base.share.classes.java.time.chrono       int dow = now1.get(ChronoField.DAY_OF_WEEK);
 java.base.share.classes.java.time.chrono       int month = now1.get(ChronoField.MONTH_OF_YEAR);
 java.base.share.classes.java.time.chrono       int year = now1.get(ChronoField.YEAR);
 java.base.share.classes.java.time.chrono       System.out.printf("  Today is %s %s %d-%s-%d%n", now1.getChronology().getId(),
 java.base.share.classes.java.time.chrono                 dow, day, month, year);
 java.base.share.classes.java.time.chrono   // Print today's date and the last day of the year for the Thai Buddhist Calendar.
 java.base.share.classes.java.time.chrono       ChronoLocalDate first = now1
 java.base.share.classes.java.time.chrono                 .with(ChronoField.DAY_OF_MONTH, 1)
 java.base.share.classes.java.time.chrono                 .with(ChronoField.MONTH_OF_YEAR, 1);
 java.base.share.classes.java.time.chrono       ChronoLocalDate last = first
 java.base.share.classes.java.time.chrono                 .plus(1, ChronoUnit.YEARS)
 java.base.share.classes.java.time.chrono                 .minus(1, ChronoUnit.DAYS);
 java.base.share.classes.java.time.chrono       System.out.printf("  %s: 1st of year: %s; end of year: %s%n", last.getChronology().getId(),
 java.base.share.classes.java.time.chrono                 first, last);
 java.base.share.classes.java.time.chrono  </pre>
 java.base.share.classes.java.time.chrono
 java.base.share.classes.java.time.chrono <p>
 java.base.share.classes.java.time.chrono This example creates and uses a date in a specific ThaiBuddhist calendar system.
 java.base.share.classes.java.time.chrono </p>
 java.base.share.classes.java.time.chrono <pre>
 java.base.share.classes.java.time.chrono   // Print the Thai Buddhist date
 java.base.share.classes.java.time.chrono       ThaiBuddhistDate now1 = ThaiBuddhistDate.now();
 java.base.share.classes.java.time.chrono       int day = now1.get(ChronoField.DAY_OF_MONTH);
 java.base.share.classes.java.time.chrono       int dow = now1.get(ChronoField.DAY_OF_WEEK);
 java.base.share.classes.java.time.chrono       int month = now1.get(ChronoField.MONTH_OF_YEAR);
 java.base.share.classes.java.time.chrono       int year = now1.get(ChronoField.YEAR);
 java.base.share.classes.java.time.chrono       System.out.printf("  Today is %s %s %d-%s-%d%n", now1.getChronology().getId(),
 java.base.share.classes.java.time.chrono                 dow, day, month, year);
 java.base.share.classes.java.time.chrono
 java.base.share.classes.java.time.chrono   // Print today's date and the last day of the year for the Thai Buddhist Calendar.
 java.base.share.classes.java.time.chrono       ThaiBuddhistDate first = now1
 java.base.share.classes.java.time.chrono                 .with(ChronoField.DAY_OF_MONTH, 1)
 java.base.share.classes.java.time.chrono                 .with(ChronoField.MONTH_OF_YEAR, 1);
 java.base.share.classes.java.time.chrono       ThaiBuddhistDate last = first
 java.base.share.classes.java.time.chrono                 .plus(1, ChronoUnit.YEARS)
 java.base.share.classes.java.time.chrono                 .minus(1, ChronoUnit.DAYS);
 java.base.share.classes.java.time.chrono       System.out.printf("  %s: 1st of year: %s; end of year: %s%n", last.getChronology().getId(),
 java.base.share.classes.java.time.chrono                 first, last);
 java.base.share.classes.java.time.chrono  </pre>
 java.base.share.classes.java.time.chrono
 java.base.share.classes.java.time.chrono <h2>Package specification</h2>
 java.base.share.classes.java.time.chrono <p>
 java.base.share.classes.java.time.chrono Unless otherwise noted, passing a null argument to a constructor or method in any class or interface
 java.base.share.classes.java.time.chrono in this package will cause a {@link java.lang.NullPointerException NullPointerException} to be thrown.
 java.base.share.classes.java.time.chrono The Javadoc "@param" definition is used to summarise the null-behavior.
 java.base.share.classes.java.time.chrono The "@throws {@link java.lang.NullPointerException}" is not explicitly documented in each method.
 java.base.share.classes.java.time.chrono </p>
 java.base.share.classes.java.time.chrono <p>
 java.base.share.classes.java.time.chrono All calculations should check for numeric overflow and throw either an {@link java.lang.ArithmeticException}
 java.base.share.classes.java.time.chrono or a {@link java.time.DateTimeException}.
 java.base.share.classes.java.time.chrono </p>
 java.base.share.classes.java.time.chrono @since 1.8
 java.base.share.classes.java.time.chrono/
package java.time.chrono;
