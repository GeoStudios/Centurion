/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.text.resources;

import java.base.share.classes.sun.util.resources.OpenListResourceBundle;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

public class JavaTimeSupplementary_en extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterNames = {
            "1st quarter",
            "2nd quarter",
            "3rd quarter",
            "4th quarter",
        };

        final String[] sharedDatePatterns = {
            "EEEE, MMMM d, y GGGG",
            "MMMM d, y GGGG",
            "MMM d, y GGGG",
            "M/d/y G",
        };

        final String[] sharedDayNames = {
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
        };

        final String[] sharedQuarterAbbreviations = {
            "Q1",
            "Q2",
            "Q3",
            "Q4",
        };

        final String[] sharedTimePatterns = {
            "h:mm:ss a zzzz",
            "h:mm:ss a z",
            "h:mm:ss a",
            "h:mm a",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "a",
            "p",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, MMMM d, y G",
            "MMMM d, y G",
            "MMM d, y G",
            "M/d/y GGGGG",
        };

        final String[] sharedEras = {
            "Before R.O.C.",
            "Minguo",
        };

        return new Object[][] {
            { "QuarterNames",
                sharedQuarterNames },
            { "calendarname.buddhist",
                "Buddhist Calendar" },
            { "calendarname.gregorian",
                "Gregorian Calendar" },
            { "calendarname.gregory",
                "Gregorian Calendar" },
            { "calendarname.islamic",
                "Islamic Calendar" },
            { "calendarname.islamic-civil",
                "Islamic Calendar (tabular, civil epoch)" },
            { "calendarname.islamic-umalqura",
                "Islamic Calendar (Umm al-Qura)" },
            { "calendarname.japanese",
                "Japanese Calendar" },
            { "calendarname.roc",
                "Minguo Calendar" },
            { "field.dayperiod",
                "AM/PM" },
            { "field.era",
                "era" },
            { "field.hour",
                "hour" },
            { "field.minute",
                "minute" },
            { "field.month",
                "month" },
            { "field.second",
                "second" },
            { "field.week",
                "week" },
            { "field.weekday",
                "day of the week" },
            { "field.year",
                "year" },
            { "field.zone",
                "time zone" },
            { "islamic.AmPmMarkers",
                new String[] {
                    "AM",
                    "PM",
                }
            },
            { "islamic.DatePatterns",
                sharedDatePatterns },
            { "islamic.DayNames",
                sharedDayNames },
            { "islamic.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "islamic.QuarterNames",
                sharedQuarterNames },
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "islamic.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.buddhist.short.Eras",
                new String[] {
                    "BC",
                    "BE",
                }
            },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.long.Eras",
                new String[] {
                    "Before Christ",
                    "Anno Domini",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "roc.DatePatterns",
                sharedDatePatterns },
            { "roc.DayAbbreviations",
                new String[] {
                    "Sun",
                    "Mon",
                    "Tue",
                    "Wed",
                    "Thu",
                    "Fri",
                    "Sat",
                }
            },
            { "roc.DayNames",
                sharedDayNames },
            { "roc.Eras",
                sharedEras },
            { "roc.MonthNames",
                new String[] {
                    "January",
                    "February",
                    "March",
                    "April",
                    "May",
                    "June",
                    "July",
                    "August",
                    "September",
                    "October",
                    "November",
                    "December",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "J",
                    "F",
                    "M",
                    "A",
                    "M",
                    "J",
                    "J",
                    "A",
                    "S",
                    "O",
                    "N",
                    "D",
                    "",
                }
            },
            { "roc.QuarterAbbreviations",
                sharedQuarterAbbreviations },
            { "roc.QuarterNames",
                sharedQuarterNames },
            { "roc.TimePatterns",
                sharedTimePatterns },
            { "roc.long.Eras",
                sharedEras },
            { "roc.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "roc.narrow.Eras",
                sharedEras },
            { "roc.short.Eras",
                sharedEras },
        };
    }
}
