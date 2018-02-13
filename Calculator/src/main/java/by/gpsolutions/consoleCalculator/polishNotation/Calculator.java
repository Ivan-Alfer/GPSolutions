package by.gpsolutions.consoleCalculator.polishNotation;

import by.gpsolutions.consoleCalculator.polishNotation.Exceptions.InvalidDataException;

import java.math.BigDecimal;
import java.util.List;

public class Calculator {
	public BigDecimal calculate(String expression) throws InvalidDataException {
		ExpressionToRPN n = new ExpressionToRPN();
		List<String> rpnExpression = n.parse(expression);

		RPNToNumber rpnToNumber = new RPNToNumber();
		return rpnToNumber.calculate(rpnExpression);
	}
}
