package test;

import JFree.DiscountManager;
import JFree.IDiscountCalculator;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiscountManagerTest {

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsFalse() throws Exception {
        // Arrange
        boolean isDiscountsSeason = false;
        double originalPrice = 100.0;
        double expectedPrice = 100.0;

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);
        mockingContext.checking(new Expectations() {
            {
                // make sure that none of the functions are called
            }
        });
        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);
        // Act


        // Assert
        // make sure that mocking Expectations Is Satisfied
        // make sure that the actual value exactly equals the expected value
        double actualPrice = discountManager.calculatePriceAfterDiscount(originalPrice);
//        System.out.println("Expected: " + expectedPrice + " Actual: " + actualPrice);
        // Assert
        assertEquals(expectedPrice, actualPrice, 0.001);
        mockingContext.assertIsSatisfied();
    }

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsTrueAndSpecialWeekIsTrue() throws Exception {
        boolean isDiscountsSeason = true;
        double originalPrice = 500.0;
        double expectedPrice = 400.0;

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);

        mockingContext.checking(new Expectations() {{
            allowing(mockedDependency).isTheSpecialWeek();
            will(returnValue(true));
        }});

        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);

        double actualPrice = discountManager.calculatePriceAfterDiscount(originalPrice);
//        System.out.println("Expected: " + expectedPrice + " Actual: " + actualPrice);
        assertEquals(expectedPrice, actualPrice, 0.001);

        mockingContext.assertIsSatisfied();
    }

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsTrueAndSpecialWeekIsFalseAndOddWeek() throws Exception {
        boolean isDiscountsSeason = true;
        double originalPrice = 500.0;
        double expectedPrice = 475.0;

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);

        mockingContext.checking(new Expectations() {{
            allowing(mockedDependency).isTheSpecialWeek();
            will(returnValue(false));
            allowing(mockedDependency).getDiscountPercentage();
            will(returnValue(5));
        }});

        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);

        double actualPrice = discountManager.calculatePriceAfterDiscount(originalPrice);
        System.out.println("Expected: " + expectedPrice + " Actual: " + actualPrice);
        assertEquals(expectedPrice, actualPrice, 0.001);

        mockingContext.assertIsSatisfied();
    }

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsTrueAndSpecialWeekIsFalseAndEvenWeek() throws Exception {
        boolean isDiscountsSeason = true;
        double originalPrice = 500.0;
        double expectedPrice = 465.0;

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);

        mockingContext.checking(new Expectations() {{
            allowing(mockedDependency).isTheSpecialWeek();
            will(returnValue(false));
            allowing(mockedDependency).getDiscountPercentage();
            will(returnValue(7));
        }});

        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);

        double actualPrice = discountManager.calculatePriceAfterDiscount(originalPrice);
        System.out.println("Expected: " + expectedPrice + " Actual: " + actualPrice);
        assertEquals(expectedPrice, actualPrice, 0.001);

        mockingContext.assertIsSatisfied();
    }
}
