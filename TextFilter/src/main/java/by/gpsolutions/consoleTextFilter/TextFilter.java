package by.gpsolutions.consoleTextFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.gpsolutions.consoleTextFilter.exceptions.InvalidInputParameter;

public class TextFilter {
	
	private final static String WORDS_DELIMETER_REGEX = "[" + Pattern.quote(";.,:")+"\\s]";
	private final static String METACHARACTERS_REGEX = Pattern.quote("<([{\\^-=$!|]})?*+.>");
	
	private List<Pattern> wordPatterns = new ArrayList<Pattern>();
	
	private StringBuilder outBuilder = new StringBuilder(); 
	private Readable in;
	private Appendable out;
	
	private Pattern getWordPattern(String parameter){
		Pattern pattern;
		
		try {
			pattern = Pattern.compile(parameter);
			
			if(!Pattern.matches(METACHARACTERS_REGEX, parameter)) {
				// check that textRegExp is simple word, not regex
				pattern = Pattern.compile("^" + parameter + "$");
			}
		} catch (Exception e) {
			String quotedParameter = Pattern.quote(parameter);
			pattern = Pattern.compile("^" + quotedParameter + "$");
		}
		
		return pattern;
	}
	
	public void setArguments(String... args) throws InvalidInputParameter {
		if (args.length == 0) {
			throw new InvalidInputParameter("Программа должна иметь хотя бы один параметр.");
		}
		
		for(String arg : args) {
			String[] subArgs = arg.split("\\s+");
			
			for(String subArg : subArgs) {
				Pattern pattern = getWordPattern(subArg);
				
				wordPatterns.add(pattern);
			}
		}
	}

	public void setInput(Readable in) {
		this.in = in;
	}

	public void setOutput(Appendable out) {
		this.out = out;
		
	}

	public void run() throws IOException {
		try(Scanner scanner = new Scanner(in)) {
			while (true) {
				String inputString = scanner.nextLine();
				
				if(inputString.length() == 0) {
					// end of the program
					break;
				}
				
				String[] words = inputString.split(WORDS_DELIMETER_REGEX);
				boolean isMatchWordInString = false;
				
				for(String word : words) {
					for(Pattern wordPattern : wordPatterns){
						
						Matcher matcher = wordPattern.matcher(word);
						
						if(matcher.find()){
							outBuilder.append(inputString)
								.append(System.lineSeparator());
							
							isMatchWordInString = true;
							break;
						}
					}
					
					if(isMatchWordInString){
						break;
					}
				}
			}
		}
		
		out.append(outBuilder);
	}
}
