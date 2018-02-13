package by.gpsolutions.consoleCalculator;

import java.util.Scanner;

import by.gpsolutions.consoleCalculator.polishNotation.Calculator;
import by.gpsolutions.consoleCalculator.polishNotation.Exceptions.InvalidDataException;

public class Program {

	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			String s = in.nextLine();
			Calculator calc = new Calculator();

			Number num = null;
			try {
				num = calc.calculate(s);
			} catch (InvalidDataException e) {
				e.getMessage();
				e.printStackTrace();
			}

			System.out.println(num);
		}
	}

}
