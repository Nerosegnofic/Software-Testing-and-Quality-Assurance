package test;

import JFree.DiscountCalculator;
import org.jfree.data.time.Week;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DiscountCalculatorTest {

    @Test
    public void testIsTheSpecialWeekWhenFalse() throws Exception {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 22);  // March 22, 2025
        Date date = calendar.getTime();
        Week week = new Week(date);

        // Act
        DiscountCalculator discountCalculator = new DiscountCalculator(week);
        boolean isSpecialWeek = discountCalculator.isTheSpecialWeek();
        // Assert
        assertFalse(isSpecialWeek);

    }

    // Test missing cases ( JUNE, 23 is a date in week 26 )
    @Test
    public void testIsTheSpecialWeekWhenTrue() throws Exception {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 23);  // June 23, 2025
        Date date = calendar.getTime();
        Week week = new Week(date);

        // Act
        DiscountCalculator discountCalculator = new DiscountCalculator(week);
        boolean isSpecialWeek = discountCalculator.isTheSpecialWeek();
        // Assert
        assertTrue(isSpecialWeek);

    }

    @Test
    public void testGetDiscountPercentageWhenEven() {
        Week week = new Week(10, 2025);
        DiscountCalculator discountCalculator = new DiscountCalculator(week);
        assertEquals(7, discountCalculator.getDiscountPercentage());
    }

    @Test
    public void testGetDiscountPercentageWhenOdd() {
        Week week = new Week(9, 2025);
        DiscountCalculator discountCalculator = new DiscountCalculator(week);
        assertEquals(5, discountCalculator.getDiscountPercentage());
    }
}
