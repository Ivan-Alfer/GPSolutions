package by.gpsolutions.consoleCalculator.tests;

import org.junit.Test;

import by.gpsolutions.consoleCalculator.polishNotation.Calculator;

import org.junit.Assert;

import java.math.BigDecimal;

public class CalculatorTest {

	@Test
	public void test1() {
		try {
			Calculator calc = new Calculator();

			BigDecimal number = calc.calculate("2 + 2 * 2");

			Assert.assertEquals(BigDecimal.valueOf(6), number);
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void test2() {
		try {
			Calculator calc = new Calculator();

			BigDecimal number = calc.calculate("(4 + 3) * 2 ^ -2");

			Assert.assertEquals(BigDecimal.valueOf(1.75), number);
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void test3() {
		try {
			Calculator calc = new Calculator();

			BigDecimal number = calc.calculate("5 + 1/0");

			Assert.fail("Exception should be thrown");
		} catch (Exception ex) {
			Assert.assertEquals("Division by zero", ex.getMessage());
		}
	}

	@Test
	public void test4() {
		try {
			Calculator calc = new Calculator();

			BigDecimal number = calc.calculate("(17 ^ 4 + 5 * 974 ^ 33 + 2.24 * 4.75)^0");

			Assert.assertEquals(BigDecimal.valueOf(1), number);
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void test5() {
		try {
			Calculator calc = new Calculator();

			BigDecimal number = calc.calculate("(17 ^ 4 + 5 * 974 ^ 33 + 2,24 * 4,75)^0");

			Assert.assertEquals(BigDecimal.valueOf(1), number);
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void test6() {
		try {
			Calculator calc = new Calculator();

			BigDecimal number = calc.calculate("4 2 * 3");

			Assert.fail("Exception should be thrown");
		} catch (Exception ex) {
			Assert.assertEquals("Invalid input data", ex.getMessage());
		}
	}

	@Test
	public void test7() {
		try {
			Calculator calc = new Calculator();

			BigDecimal number = calc.calculate("4a * 5");

			Assert.fail("Exception should be thrown");
		} catch (Exception ex) {
			Assert.assertEquals("Invalid input data", ex.getMessage());
		}
	}

	@Test
	public void test8() {
		try {
			Calculator calc = new Calculator();

			BigDecimal number = calc.calculate("4 , 6 * 5");

			Assert.fail("Exception should be thrown");
		} catch (Exception ex) {
			Assert.assertEquals("Invalid input data", ex.getMessage());
		}
	}

	@Test
	public void test9() {
		try {
			Calculator calc = new Calculator();

			BigDecimal number = calc.calculate("      4      *         5         ");

			Assert.assertEquals(BigDecimal.valueOf(20), number);
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}
}
