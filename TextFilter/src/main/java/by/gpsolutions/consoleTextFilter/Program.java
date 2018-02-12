package by.gpsolutions.consoleTextFilter;

import java.io.InputStreamReader;

public class Program {

	public static void main(String[] args) {
		try {
			TextFilter textFilter = new TextFilter();
			textFilter.setArguments(args);
			textFilter.setInput(new InputStreamReader(System.in));
			textFilter.setOutput(System.out);
			
			textFilter.run();
		} catch(Exception ex){
			System.err.println(ex.getMessage());
		}
	}
}
