package by.gpsolutions.consoleCalculator.polishNotation;

import by.gpsolutions.consoleCalculator.polishNotation.Exceptions.InvalidDataException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionToRPN {
	private static String operators = "+-*/^";
	private static String delimiters = "() " + operators;
	private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9\\.\\,]+$");

	private static boolean isAcceptableToken(String token) {
		return isDelimiter(token) || isNumber(token) || Arrays.asList(new String[]{".", ","}).contains(token);
	}
	
	private static boolean isDelimiter(String token) {
		if (token.length() != 1) {
			return false;
		}
		
		for (int i = 0; i < delimiters.length(); i++) {
			if (token.charAt(0) == delimiters.charAt(i)) { 
				return true;
			}
		}
		return false;
	}

	private static boolean isNumber(String token){
		//return Arrays.asList(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}).contains(token);
		Matcher matcher = NUMBER_PATTERN.matcher(token);
		return matcher.matches();
	}
	
	private static boolean isOperator(String token) {
		if (token.equals("u-")) { 
			return true;
		}
		
		for (int i = 0; i < operators.length(); i++) {
			if (token.charAt(0) == operators.charAt(i)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean isFunction(String token) {
		if (token.equals("^")) {
			return true;
		}
		return false;
	}
	
	private static int priority(String token) {
		if (token.equals("(")) return 1;
		if (token.equals("+") || token.equals("-")) return 2;
		if (token.equals("*") || token.equals("/")) return 3;
		return 4;
	}
	
	public List<String> parse(String infix) throws InvalidDataException {
		List<String> postfix = new ArrayList<String>();
		Deque<String> stack = new ArrayDeque<String>();
		
		if("".equals(infix)) {
			throw new InvalidDataException("Invalid input data");
		}
			
		StringTokenizer tokenizer = new StringTokenizer(infix, delimiters, true);
		String prev = "";
		String curr = "";

		boolean isPrevWhiteSpace = false;

		while (tokenizer.hasMoreTokens()) {
			curr = tokenizer.nextToken();
			if (!tokenizer.hasMoreTokens() && isOperator(curr)) {
				throw new InvalidDataException("Invalid input data");
			}

			if(!isAcceptableToken(curr)){
				throw new InvalidDataException("Invalid input data");
			}

			if(isPrevWhiteSpace && isNumber(prev) && isNumber(curr)){
				throw new InvalidDataException("Invalid input data");
			}
			
			if (" ".equals(curr)) {
				isPrevWhiteSpace = true;
				continue;
			}
			isPrevWhiteSpace = false;

			if (isFunction(curr)) stack.push(curr);
			else if (isDelimiter(curr)) {
				if (curr.equals("(")) stack.push(curr);
				else if (curr.equals(")")) {
					while (!stack.peek().equals("(")) {
						postfix.add(stack.pop());
						if (stack.isEmpty()) {
							throw new InvalidDataException("Invalid input data");
						}
					}
					stack.pop();
					if (!stack.isEmpty() && isFunction(stack.peek())) {
						postfix.add(stack.pop());
					}
				}
				else {
					if (curr.equals("-") && (prev.equals("") || (isDelimiter(prev)  && !prev.equals(")")))) {
						// унарный минус
						curr = "u-";
					}
					else {
						while (!stack.isEmpty() && (priority(curr) <= priority(stack.peek()))) {
							postfix.add(stack.pop());
						}
						
					}
					stack.push(curr);
				}
				
			}
			
			else {
				postfix.add(curr);
			}
			prev = curr;
		}
		
		while (!stack.isEmpty()) {
			if (isOperator(stack.peek())) postfix.add(stack.pop());
			else {
				throw new InvalidDataException("Invalid input data");
			}
		}
		return postfix;
	}
}
