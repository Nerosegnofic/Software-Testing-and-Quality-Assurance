package test;

import org.jfree.data.time.Week;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WeekTest {
    Week week;

    private void arrange() {
        week = new Week();
    }
    @Test
    public void testWeekDefaultCtor() {
        arrange();
        assertEquals(2025, week.getYear().getYear());
        assertEquals(12, week.getWeek()); // to be updated with the current week
    }

}
