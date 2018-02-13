package by.gpsolutions.consoleCalculator.tests;

import by.gpsolutions.consoleCalculator.polishNotation.RPNToNumber;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class RPNToNumberTest {
    @Test
    public void test1(){
        try {
            RPNToNumber rpnToNumber = new RPNToNumber();

            BigDecimal number = rpnToNumber.calculate(Arrays.asList(new String[]{"2","2","2","*","+"}));

            Assert.assertEquals(BigDecimal.valueOf(6), number);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void test2(){
        try {
            RPNToNumber rpnToNumber = new RPNToNumber();

            BigDecimal number = rpnToNumber.calculate(Arrays.asList(new String[]{"17","4","^","5","974","33","^","*","+","2.24","4.75","*","+","0","^"}));

            Assert.assertEquals(BigDecimal.valueOf(1), number);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void test3(){
        try {
            RPNToNumber rpnToNumber = new RPNToNumber();

            BigDecimal number = rpnToNumber.calculate(Arrays.asList(new String[]{"1.1","2.1","*"}));

            Assert.assertEquals(BigDecimal.valueOf(2.31), number);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }
}
