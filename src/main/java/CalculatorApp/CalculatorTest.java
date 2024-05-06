package CalculatorApp;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestListenerClass.class)
public class CalculatorTest {
    private Calculator calculator;

   public CalculatorTest()
   {
       calculator = new Calculator();
   }

    @Test(groups = {"positive"})
    public void testAdditionPositive()
    {
        Assert.assertEquals(calculator.sum(5, 10), 15);
    }

    @Test(groups = {"negative"})
    public void testAdditionNegative()
    {
        Assert.assertNotEquals(calculator.sum(5, 5), 15);
    }

    @Test(groups = {"positive"})
    public void testSubtractionPositive()
    {
        Assert.assertEquals(calculator.sub(10, 5), 5);
    }

    @Test(groups = {"negative"})
    public void testSubtractionNegative()
    {
        Assert.assertNotEquals(calculator.sub(10, 5), 7);
    }

    @Test(groups = {"positive"})
    public void testMultiplicationPositive()
    {
        Assert.assertEquals(calculator.mult(5, 5), 25);
    }

    @Test(groups = {"negative"})
    public void testMultiplicationNegative()
    {
        Assert.assertNotEquals(calculator.mult(5, 5), 30);
    }

    @Test(groups = {"positive"})
    public void testDivisionPositive()
    {
        Assert.assertEquals(calculator.div(10, 2), 5);
    }

//    @Test(expectedExceptions = ArithmeticException.class, groups = {"negative"})
//    public void testDivisionByZero()
//    {
//        calculator.div(10, 0);
//    }

    @DataProvider(name = "inputNumbers")
    public Object[][] createData1() {
        return new Object[][] {
                { 10, 2, 5 },
                { 20, 4, 5 },
        };
    }

    // Parameterized test using dataProvider
    @Test(dataProvider = "inputNumbers", groups = {"positive"})
    public void testDivisionParameterized(int a, int b, int expected) {
        Assert.assertEquals(calculator.div(a, b), expected);
    }

    @AfterMethod
    public void tearDown() {
        calculator = null;
    }
}
