package com.java.calc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

MathContext mc = new MathContext(11);
BigDecimal bd1 = new BigDecimal("123456789",mc);
System.out.println("BigDecimal(10) with Scale 10 toString = "+bd1);



	}

}
