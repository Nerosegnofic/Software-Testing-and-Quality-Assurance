package test;

import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Week;
import org.jfree.data.time.Year;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class WeekTest {
    Week week;
    Year year;
    Date date;


    private void arrange() {
        week = new Week();
    }
    private void ctor2() {week = new Week(16, 2025);}
    private void ctor3() {year = new Year(2025); week = new Week(14, year);}
    private void ctor4() {date = new Date(); week = new Week(date);}
    private void ctor5() {date = new Date(); week = new Week(date, TimeZone.getDefault(), Locale.getDefault());}

    @Test
    public void testWeekDefaultCtor() {
        arrange();
        assertEquals(2025, week.getYear().getYear());
        assertEquals(14, week.getWeek()); // to be updated with the current week
    }

    @Test
    public void testWeekCtor2() {
        ctor2();
        assertEquals(2025, week.getYearValue());
        assertEquals(16, week.getWeek());
    }

    @Test
    public void testWeekCtor3() {
        ctor3();
        assertEquals(year, week.getYear());
        assertEquals(14, week.getWeek());
    }

    @Test
    public void testWeekCtor4() {
        ctor4();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int expectedWeek = cal.get(Calendar.WEEK_OF_YEAR);
        int expectedYear = cal.get(Calendar.YEAR);

        assertEquals(expectedWeek, week.getWeek());
        assertEquals(expectedYear, week.getYearValue());
    }

    @Test
    public void testWeekCtor5() {
        ctor5();

        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        cal.setTime(date);
        int expectedWeek = cal.get(Calendar.WEEK_OF_YEAR);
        int expectedYear = cal.get(Calendar.YEAR);

        assertEquals(expectedWeek, week.getWeek());
        assertEquals(expectedYear, week.getYearValue());

        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        assertEquals(cal.getTimeInMillis(), week.getStart().getTime());
    }

    @Test
    public void testGetWeek() {
        week = new Week(10, 2025);
        assertEquals(10, week.getWeek());
    }

    @Test
    public void testGetYear() {
        Year year = new Year(2025);
        assertEquals(2025, year.getYear());
    }

    @Test
    public void testGetYearValue() {
        Week week = new Week(10, 2025);
        assertEquals(2025, week.getYearValue());
    }

    @Test
    public void testGetFirstMillisecond() {
        Week week1 = new Week(10, 2025);
        Week week2 = new Week(9, 2025);
        long expected = week2.getLastMillisecond() + 1;
        assertEquals(expected, week1.getFirstMillisecond());
    }

    @Test
    public void testGetMiddleMillisecond() {
        Week week = new Week(10, 2025);
        long expected = week.getFirstMillisecond() +
                (week.getLastMillisecond() - week.getFirstMillisecond()) / 2;
        assertEquals(expected, week.getMiddleMillisecond());
    }

    @Test
    public void testGetLastMillisecond() {
        Week week1 = new Week(10, 2025);
        Week week2 = new Week(11, 2025);
        long expected = week2.getFirstMillisecond() - 1;
        assertEquals(expected, week1.getLastMillisecond());
    }

    @Test
    public void testGetFirstMillisecondWithCalendar() {
        Week week = new Week(10, 2025);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2025);
        cal.set(Calendar.WEEK_OF_YEAR, 10);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        long expectedFirstMillisecond = cal.getTimeInMillis();
        assertEquals(expectedFirstMillisecond, week.getFirstMillisecond(cal));
    }

    @Test
    public void testGetMiddleMillisecondWithCalendar() {
        Week week = new Week(10, 2025);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2025);
        cal.set(Calendar.WEEK_OF_YEAR, 10);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        long expectedMiddle = week.getFirstMillisecond(cal) +
                (week.getLastMillisecond(cal) - week.getFirstMillisecond(cal)) / 2;

        assertEquals(expectedMiddle, week.getMiddleMillisecond(cal));
    }

    @Test
    public void testGetLastMillisecondWithCalendar() {
        Week week = new Week(10, 2025);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2025);
        cal.set(Calendar.WEEK_OF_YEAR, 11);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        long expectedLastMillisecond = cal.getTimeInMillis();
        assertEquals(expectedLastMillisecond, week.getLastMillisecond(cal) + 1);
    }

    @Test
    public void testGetFirstMillisecondWithTimeZone() {
        Week week = new Week(10, 2025);
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.set(Calendar.YEAR, 2025);
        cal.set(Calendar.WEEK_OF_YEAR, 10);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        long expectedFirstMillisecond = cal.getTimeInMillis();
        assertEquals(expectedFirstMillisecond, week.getFirstMillisecond(TimeZone.getDefault()));
    }

    @Test
    public void testGetMiddleMillisecondWithTimeZone() {
        Week week = new Week(10, 2025);
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.set(Calendar.YEAR, 2025);
        cal.set(Calendar.WEEK_OF_YEAR, 10);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        long expectedMiddleMillisecond = week.getFirstMillisecond(cal) +
                (week.getLastMillisecond(cal) - week.getFirstMillisecond(cal)) / 2;
        assertEquals(expectedMiddleMillisecond, week.getMiddleMillisecond(TimeZone.getDefault()));
    }

    @Test
    public void testGetLastMillisecondWithTimeZone() {
        Week week = new Week(10, 2025);
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.set(Calendar.YEAR, 2025);
        cal.set(Calendar.WEEK_OF_YEAR, 11);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        long expectedLastMillisecond = cal.getTimeInMillis();
        assertEquals(expectedLastMillisecond, week.getLastMillisecond(TimeZone.getDefault()) + 1);
    }

    @Test
    public void testGetFirstMillisecondWithTimeZoneAndLocale() {
        Week week = new Week(10, 2025);

        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        cal.set(Calendar.YEAR, 2025);
        cal.set(Calendar.WEEK_OF_YEAR, 10);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        long expectedFirstMillisecond = week.getFirstMillisecond(cal);

        assertEquals(expectedFirstMillisecond, week.getFirstMillisecond());
    }

    @Test
    public void testGetMiddleMillisecondWithTimeZoneAndLocale() {
        Week week = new Week(10, 2025);

        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        cal.set(Calendar.YEAR, 2025);
        cal.set(Calendar.WEEK_OF_YEAR, 10);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        long expectedMiddleMillisecond = week.getFirstMillisecond(cal) +
                (week.getLastMillisecond(cal) - week.getFirstMillisecond(cal)) / 2;

        assertEquals(expectedMiddleMillisecond, week.getMiddleMillisecond(cal));
    }

    @Test
    public void testGetLastMillisecondWithTimeZoneAndLocale() {
        Week week = new Week(10, 2025);

        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        cal.set(Calendar.YEAR, 2025);
        cal.set(Calendar.WEEK_OF_YEAR, 11);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        long expectedLastMillisecond = cal.getTimeInMillis();

        assertEquals(expectedLastMillisecond, week.getLastMillisecond() + 1);
    }

    @Test
    public void testPrevious() {
        Week currentWeek = new Week(10, 2025);
        Week previousWeek = (Week) currentWeek.previous();
        assertNotNull(previousWeek); // Will be null if week 1, year 1900
        assertEquals(9, previousWeek.getWeek());
        assertEquals(2025, previousWeek.getYearValue());
    }

    @Test
    public void testNext() {
        Week currentWeek = new Week(10, 2025);
        Week previousWeek = (Week) currentWeek.next();
        assertNotNull(previousWeek); // Will be null if week 53, year 9999
        assertEquals(11, previousWeek.getWeek());
        assertEquals(2025, previousWeek.getYearValue());
    }

    @Test
    public void testGetSerialIndex() {
        Week week = new Week(10, 2025);
        long expectedSerialIndex = 2025L * 53 + 10;
        assertEquals(expectedSerialIndex, week.getSerialIndex());
    }

    @Test
    public void testToString() {
        Week week = new Week(10, 2025);
        assertEquals("Week 10, 2025", week.toString());
    }

    @Test
    public void testEquals() {
        Week week1 = new Week(10, 2025);
        Week week2 = new Week(10, 2025);
        Week week3 = new Week(11, 2025);
        Week week4 = new Week(10, 2026);
        Object notAWeek = new Object();

        assertTrue(week1.equals(week1));
        assertTrue(week1.equals(week2));
        assertTrue(week2.equals(week1));

        assertFalse(week1.equals(week3));
        assertFalse(week1.equals(week4));

        assertFalse(week1.equals(null));
        assertFalse(week1.equals(notAWeek));
    }

    @Test
    public void testHashCode() {
        Week week1 = new Week(10, 2025);
        Week week2 = new Week(10, 2025);
        Week week3 = new Week(11, 2025);
        Week week4 = new Week(10, 2026);

        assertEquals(week1.hashCode(), week2.hashCode());

        assertNotEquals(week1.hashCode(), week3.hashCode());
        assertNotEquals(week1.hashCode(), week4.hashCode());
    }

    @Test
    public void testCompareTo() {
        Week week1 = new Week(10, 2025);
        Week week2 = new Week(10, 2025);
        Week week3 = new Week(11, 2025);

        assertEquals(0, week1.compareTo(week1));
        assertEquals(0, week1.compareTo(week2));

        assertTrue(week1.compareTo(week3) < 0);
        assertTrue(week3.compareTo(week1) > 0);
    }
}
