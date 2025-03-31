package test;

import org.jfree.data.time.Week;
import org.jfree.data.time.Year;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

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


}
