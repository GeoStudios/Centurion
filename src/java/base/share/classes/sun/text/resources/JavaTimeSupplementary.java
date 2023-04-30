/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.text.resources;

import java.base.share.classes.sun.util.resources.OpenListResourceBundle;

/**
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

public class JavaTimeSupplementary extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedQuarterNames = {
            "Q1",
            "Q2",
            "Q3",
            "Q4",
        };

        final String[] sharedQuarterNarrows = {
            "1",
            "2",
            "3",
            "4",
        };

        final String[] sharedDatePatterns = {
            "GGGG y MMMM d, EEEE",
            "GGGG y MMMM d",
            "GGGG y MMM d",
            "G y-MM-dd",
        };

        final String[] sharedDayNames = {
            "Sun",
            "Mon",
            "Tue",
            "Wed",
            "Thu",
            "Fri",
            "Sat",
        };

        final String[] sharedDayNarrows = {
            "S",
            "M",
            "T",
            "W",
            "T",
            "F",
            "S",
        };

        final String[] sharedEras = {
            "",
            "AH",
        };

        final String[] sharedMonthNarrows = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "",
        };

        final String[] sharedTimePatterns = {
            "HH:mm:ss zzzz",
            "HH:mm:ss z",
            "HH:mm:ss",
            "HH:mm",
        };

        final String[] sharedAmPmMarkers = {
            "AM",
            "PM",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "G y MMMM d, EEEE",
            "G y MMMM d",
            "G y MMM d",
            "GGGGG y-MM-dd",
        };

        final String[] sharedJavaTimeLongEras = {
            "",
            "Meiji",
            "Taisho",
            "Showa",
            "Heisei",
            "Reiwa",
        };

        final String[] sharedShortEras = {
            "Before R.O.C.",
            "R.O.C.",
        };

        final String[] sharedMonthNames = {
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "Dec",
            "",
        };

        return new Object[][] {
            { "QuarterAbbreviations",
                sharedQuarterNames },
            { "QuarterNames",
                sharedQuarterNames },
            { "QuarterNarrows",
                sharedQuarterNarrows },
            { "field.dayperiod",
                "Dayperiod" },
            { "field.era",
                "Era" },
            { "field.hour",
                "Hour" },
            { "field.minute",
                "Minute" },
            { "field.month",
                "Month" },
            { "field.second",
                "Second" },
            { "field.week",
                "Week" },
            { "field.weekday",
                "Day of the Week" },
            { "field.year",
                "Year" },
            { "field.zone",
                "Zone" },
            { "islamic.DatePatterns",
                sharedDatePatterns },
            { "islamic.DayAbbreviations",
                sharedDayNames },
            { "islamic.DayNames",
                sharedDayNames },
            { "islamic.DayNarrows",
                sharedDayNarrows },
            { "islamic.Eras",
                sharedEras },
            { "islamic.MonthAbbreviations",
                new String[] {
                    "Muh.",
                    "Saf.",
                    "Rab. I",
                    "Rab. II",
                    "Jum. I",
                    "Jum. II",
                    "Raj.",
                    "Sha.",
                    "Ram.",
                    "Shaw.",
                    "Dhu\u02bbl-Q.",
                    "Dhu\u02bbl-H.",
                    "",
                }
            },
            { "islamic.MonthNames",
                new String[] {
                    "Muharram",
                    "Safar",
                    "Rabi\u02bb I",
                    "Rabi\u02bb II",
                    "Jumada I",
                    "Jumada II",
                    "Rajab",
                    "Sha\u02bbban",
                    "Ramadan",
                    "Shawwal",
                    "Dhu\u02bbl-Qi\u02bbdah",
                    "Dhu\u02bbl-Hijjah",
                    "",
                }
            },
            { "islamic.MonthNarrows",
                sharedMonthNarrows },
            { "islamic.QuarterNames",
                sharedQuarterNames },
            { "islamic.QuarterNarrows",
                sharedQuarterNarrows },
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "islamic.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.long.Eras",
                sharedEras },
            { "islamic.narrow.Eras",
                sharedEras },
            { "islamic.short.Eras",
                sharedEras },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.buddhist.long.Eras",
                new String[] {
                    "BC",
                    "BE",
                }
            },
            { "java.time.buddhist.short.Eras",
                new String[] {
                    "BC",
                    "B.E.",
                }
            },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                new String[] {
                    "G y MMMM d (EEEE)",
                    "G y MMMM d",
                    "G y MMM d",
                    "GGGGGy.MM.dd",
                }
            },
            { "java.time.japanese.long.Eras",
                sharedJavaTimeLongEras },
            { "java.time.japanese.short.Eras",
                sharedJavaTimeLongEras },
            { "java.time.long.Eras",
                new String[] {
                    "BCE",
                    "CE",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "BC",
                    "AD",
                }
            },
            { "roc.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.DatePatterns",
                sharedDatePatterns },
            { "roc.DayNames",
                sharedDayNames },
            { "roc.DayNarrows",
                sharedDayNarrows },
            { "roc.Eras",
                sharedShortEras },
            { "roc.MonthAbbreviations",
                sharedMonthNames },
            { "roc.MonthNames",
                sharedMonthNames },
            { "roc.MonthNarrows",
                sharedMonthNarrows },
            { "roc.QuarterNames",
                sharedQuarterNames },
            { "roc.QuarterNarrows",
                sharedQuarterNarrows },
            { "roc.TimePatterns",
                sharedTimePatterns },
            { "roc.abbreviated.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.long.Eras",
                sharedShortEras },
            { "roc.narrow.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.narrow.Eras",
                sharedShortEras },
            { "roc.short.Eras",
                sharedShortEras },
            { "timezone.gmtFormat",
                "GMT{0}" },
            { "timezone.hourFormat",
                "+HH:mm;-HH:mm" },
        };
    }
}
