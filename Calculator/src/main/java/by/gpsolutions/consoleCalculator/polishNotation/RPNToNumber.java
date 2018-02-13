package by.gpsolutions.consoleCalculator.polishNotation;

import by.gpsolutions.consoleCalculator.polishNotation.Exceptions.InvalidDataException;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class RPNToNumber {
	public BigDecimal calculate(List<String> postfix) throws InvalidDataException {
		Deque<BigDecimal> stack = new ArrayDeque<BigDecimal>();
		for (String x : postfix) {
			if (x.equals("^")){
				BigDecimal b = stack.pop();
				BigDecimal a = stack.pop();

				if(b.signum() < 0) {
					a = BigDecimal.valueOf(1).divide(a);
					b = b.multiply(BigDecimal.valueOf(-1));
				}

				BigDecimal result = a.pow(b.intValue());

				stack.push(result);
			}
			else if (x.equals("+")){
				BigDecimal b = stack.pop();
				BigDecimal a = stack.pop();

				BigDecimal result = a.add(b);

				stack.push(result);
			}
			else if (x.equals("-")) {
				BigDecimal b = stack.pop();
				BigDecimal a = stack.pop();

				BigDecimal result = a.subtract(b);

				stack.push(result);
			}
			else if (x.equals("*")) {
				BigDecimal b = stack.pop();
				BigDecimal a = stack.pop();

				BigDecimal result = a.multiply(b);

				stack.push(result);
			}
			else if (x.equals("/")) {
				BigDecimal b = stack.pop();

				if (BigDecimal.ZERO.equals(b)) {
					throw new InvalidDataException("Division by zero");
				}

				BigDecimal a = stack.pop();

				BigDecimal result = a.divide(b);
				stack.push(result);
			}
			else if (x.equals("u-")) {
				BigDecimal a = stack.pop();

				BigDecimal result = a.multiply(BigDecimal.valueOf(-1));
				stack.push(result);
			}
			else {
				stack.push(new BigDecimal(x.replace(',', '.')));
			}
		}
		return stack.pop();
	}
}
