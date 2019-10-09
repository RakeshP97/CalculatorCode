package com.java.calc.service;

import org.junit.Assert;
import org.junit.Test;


import junit.framework.TestCase;

public class TestCalculatorService extends TestCase {

String val1="6";
String val2="7";
	
private ICalculatorService calculatorService = new CalculatorService();

@Test
public void testCalculateAdd()
{
	double expected = new Double(6+7);
	String input = val1+"+"+ val2;
	double result = calculatorService.calculate(input);
	Assert.assertEquals(expected, result,.00);
}
@Test
public void testCalculateSqrt()
{
	double expected = new Double(Math.sqrt(7.0));
	String input = "@"+ val2;
	double result = calculatorService.calculate(input);
	Assert.assertEquals(expected, result,.00);
}

@Test
public void testCalculateSub()
{
	double expected = new Double(6-7);
	String input = val1+"-"+ val2;
	double result = calculatorService.calculate(input);
	Assert.assertEquals(expected, result,.00);
}

@Test
public void testCalculateMul()
{
	double expected = new Double(6*7);
	String input = val1+"*"+ val2;
	double result = calculatorService.calculate(input);
	Assert.assertEquals(expected, result,.00);
}

@Test
public void testCalculateDiv()
{
	double expected = new Double(35/7);
	String input = 35+"/"+ val2;
	double result = calculatorService.calculate(input);
	Assert.assertEquals(expected, result,.00);
}

@Test
public void testgetPreviousValue()
{
	double result = calculatorService.getPreviousValue();
	Assert.assertEquals(calculatorService.getPreviousValue(), result,.00);
}
}
