package by.gpsolutions.consoleTextFilter.tests;

import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;

import by.gpsolutions.consoleTextFilter.TextFilter;

public class TextFilterTest {
	
	@Test
	public void test1(){
		try {
			TextFilter textFilter = new TextFilter();
			textFilter.setArguments("abcd");
			textFilter.setInput(new StringReader("abcdf;" + System.lineSeparator()
				+ "abcd abc;" + System.lineSeparator() 
				+ "bcd adbc abc;" + System.lineSeparator()
				+ "ghij abcd acdf;" + System.lineSeparator() 
				+ "nab ac ad;"  + System.lineSeparator()
				+ System.lineSeparator()));
			StringWriter out = new StringWriter();
			textFilter.setOutput(out);
			
			textFilter.run();
			
			String outString = out.toString();
			
			Assert.assertEquals(outString, "abcd abc;" + System.lineSeparator() + "ghij abcd acdf;" + System.lineSeparator());
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}
	
	@Test
	public void test2(){
		try {
			TextFilter textFilter = new TextFilter();
			textFilter.setArguments("abcd", "abc");
			textFilter.setInput(new StringReader("abcdf;" + System.lineSeparator()
				+ "abcd abc;" + System.lineSeparator() 
				+ "bcd adbc abc;" + System.lineSeparator()
				+ "ghij abcd acdf;" + System.lineSeparator() 
				+ "nab ac ad;"  + System.lineSeparator()
				+ System.lineSeparator()));
			StringWriter out = new StringWriter();
			textFilter.setOutput(out);
			
			textFilter.run();
			
			String outString = out.toString();
			
			Assert.assertEquals(outString, "abcd abc;" + System.lineSeparator() + "bcd adbc abc;"+ System.lineSeparator() + "ghij abcd acdf;" + System.lineSeparator());
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}
	
	@Test
	public void test3(){
		try {
			TextFilter textFilter = new TextFilter();
			textFilter.setArguments("^ab.+");
			textFilter.setInput(new StringReader("abcdf;" + System.lineSeparator()
				+ "abcd abc;" + System.lineSeparator() 
				+ "bcd adbc abc;" + System.lineSeparator()
				+ "ghij abcd acdf;" + System.lineSeparator() 
				+ "nab ac ad;"  + System.lineSeparator()
				+ System.lineSeparator()));
			StringWriter out = new StringWriter();
			textFilter.setOutput(out);
			
			textFilter.run();
			
			String outString = out.toString();
			
			Assert.assertEquals(outString, "abcdf;" + System.lineSeparator() + "abcd abc;" + System.lineSeparator() + "bcd adbc abc;"+ System.lineSeparator() + "ghij abcd acdf;" + System.lineSeparator());
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}
	
	@Test
	public void test4(){
		try {
			TextFilter textFilter = new TextFilter();
			textFilter.setArguments("pqrst");
			textFilter.setInput(new StringReader("abcdf;" + System.lineSeparator()
				+ "abcd abc;" + System.lineSeparator() 
				+ "bcd adbc abc;" + System.lineSeparator()
				+ "ghij abcd acdf;" + System.lineSeparator() 
				+ "nab ac ad;"  + System.lineSeparator()
				+ System.lineSeparator()));
			StringWriter out = new StringWriter();
			textFilter.setOutput(out);
			
			textFilter.run();
			
			String outString = out.toString();
			
			Assert.assertEquals(outString, "");
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}
	
	@Test
	public void test5(){
		try {
			TextFilter textFilter = new TextFilter();
			textFilter.setArguments("abcd");
			textFilter.setInput(new StringReader("abcdf;" + System.lineSeparator()
				+ "abcd abc;" + System.lineSeparator() 
				+ "bcd adbc abc;" + System.lineSeparator()
				+ "ghij abcd acdf;" + System.lineSeparator() 
				+ "nab ac ad;"  + System.lineSeparator()
				+ System.lineSeparator()
				+ "abcd SHOULD NOT WRITE IN OUT;"  + System.lineSeparator()
				+ " abcd SHOULD NOT WRITE IN OUT;"  + System.lineSeparator()));
			StringWriter out = new StringWriter();
			textFilter.setOutput(out);
			
			textFilter.run();
			
			String outString = out.toString();
			
			Assert.assertEquals(outString, "abcd abc;" + System.lineSeparator() + "ghij abcd acdf;" + System.lineSeparator());
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}
	
	@Test
	public void test6(){
		try {
			TextFilter textFilter = new TextFilter();
			textFilter.setArguments("*");
			textFilter.setInput(new StringReader("abcdf;" + System.lineSeparator()
				+ "abcd * abc;" + System.lineSeparator() 
				+ "bcd ad*bc abc;" + System.lineSeparator()
				+ "ghij abcd acdf; *" + System.lineSeparator() 
				+ "*nab ac ad;"  + System.lineSeparator()
				+ System.lineSeparator()
				+ "abcd SHOULD NOT WRITE IN OUT;"  + System.lineSeparator()
				+ " abcd SHOULD NOT WRITE IN OUT;"  + System.lineSeparator()));
			StringWriter out = new StringWriter();
			textFilter.setOutput(out);
			
			textFilter.run();
			
			String outString = out.toString();
			
			Assert.assertEquals(outString, "abcd * abc;" + System.lineSeparator() + "ghij abcd acdf; *" + System.lineSeparator());
		} catch (Exception ex) {
			Assert.fail(ex.getMessage());
		}
	}
}
