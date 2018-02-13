package by.gpsolutions.consoleCalculator.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import by.gpsolutions.consoleCalculator.polishNotation.ExpressionToRPN;

public class ExpressionToRPNTest {
	@Test
	public void test1() {
		try {
			ExpressionToRPN eToRPN = new ExpressionToRPN();
			
			List<String> rpn = eToRPN.parse("2 + 2 * 2");
			
			String rpnString = String.join(",", rpn);
			
			Assert.assertEquals("2,2,2,*,+", rpnString);
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}
	
	@Test
	public void test2() {
		try {
			ExpressionToRPN eToRPN = new ExpressionToRPN();
			
			List<String> rpn = eToRPN.parse("(4 + 3) * 2 ^ -2");
			
			String rpnString = String.join(",", rpn);
			
			Assert.assertEquals("4,3,+,2,2,u-,^,*", rpnString);
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}
	
	@Test
	public void test3() {
		try {
			ExpressionToRPN eToRPN = new ExpressionToRPN();
			
			List<String> rpn = eToRPN.parse("5 + 1/0");
			
			String rpnString = String.join(",", rpn);
			
			Assert.assertEquals("5,1,0,/,+", rpnString);
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}
	
	@Test
	public void test4() {
		try {
			ExpressionToRPN eToRPN = new ExpressionToRPN();
			
			List<String> rpn = eToRPN.parse("(1+2) ^ 0");
			
			String rpnString = String.join(",", rpn);

			Assert.assertEquals("1,2,+,0,^", rpnString);
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}
	
	@Test
	public void test5() {
		try {
			ExpressionToRPN eToRPN = new ExpressionToRPN();
			
			List<String> rpn = eToRPN.parse("4 2 * 3");
			
			Assert.fail("Exception should be thrown");
		} catch (Exception ex) {
			Assert.assertEquals("Invalid input data", ex.getMessage());
		}
	}
	
	@Test
	public void test6() {
		try {
			ExpressionToRPN eToRPN = new ExpressionToRPN();
			
			List<String> rpn = eToRPN.parse("4a * 5");
			
			Assert.fail("Exception should be thrown");
		} catch (Exception ex) {
			Assert.assertEquals("Invalid input data", ex.getMessage());
		}
	}

	@Test
	public void test7() {
		try {
			ExpressionToRPN eToRPN = new ExpressionToRPN();

			List<String> rpn = eToRPN.parse("(17 ^ 4 + 5 * 974 ^ 33 + 2.24 * 4.75)^0");

			String rpnString = String.join(",", rpn);

			Assert.assertEquals("17,4,^,5,974,33,^,*,+,2.24,4.75,*,+,0,^", rpnString);
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void test8() {
		try {
			ExpressionToRPN eToRPN = new ExpressionToRPN();

			List<String> rpn = eToRPN.parse("2^-1");

			String rpnString = String.join(",", rpn);

			Assert.assertEquals("2,1,u-,^", rpnString);
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void test9() {
		try {
			ExpressionToRPN eToRPN = new ExpressionToRPN();

			List<String> rpn = eToRPN.parse("-2^-1");

			String rpnString = String.join(",", rpn);

			Assert.assertEquals("2,1,u-,^,u-", rpnString);
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}
	
	@Test
	public void test10() {
		try {
			ExpressionToRPN eToRPN = new ExpressionToRPN();
			
			List<String> rpn = eToRPN.parse("");
			
			Assert.fail("Exception should be thrown");
		} catch (Exception ex) {
			Assert.assertEquals("Invalid input data", ex.getMessage());
		}
	}
}
