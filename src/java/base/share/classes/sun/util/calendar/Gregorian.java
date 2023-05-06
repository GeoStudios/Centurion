/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.util.calendar;

import java.util.TimeZone;

/**
 * Gregorian calendar implementation.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

public class Gregorian extends BaseCalendar {

    static class Date extends BaseCalendar.Date {
        protected Date() {
            super();
        }

        protected Date(TimeZone zone) {
            super(zone);
        }

        public int getNormalizedYear() {
            return getYear();
        }

        public void setNormalizedYear(int normalizedYear) {
            setYear(normalizedYear);
        }
    }

    Gregorian() {
    }

    public String getName() {
        return "gregorian";
    }

    public Date getCalendarDate() {
        return getCalendarDate(System.currentTimeMillis(), newCalendarDate());
    }

    public Date getCalendarDate(long millis) {
        return getCalendarDate(millis, newCalendarDate());
    }

    public Date getCalendarDate(long millis, CalendarDate date) {
        return (Date) super.getCalendarDate(millis, date);
    }

    public Date getCalendarDate(long millis, TimeZone zone) {
        return getCalendarDate(millis, newCalendarDate(zone));
    }

    public Date newCalendarDate() {
        return new Date();
    }

    public Date newCalendarDate(TimeZone zone) {
        return new Date(zone);
    }
}
