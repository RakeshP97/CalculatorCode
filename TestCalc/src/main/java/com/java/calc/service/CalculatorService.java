package com.java.calc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.java.calc.common.Operator;

@Service
public class CalculatorService implements ICalculatorService {
	
	private static double prevResults =0.0;

	private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorService.class);


    /**
     * Method is used for calculation for given input by parsing the input
     *
     * @param input
     * @return
     */
	@Override
	public double calculate(String input) {
		
		LOGGER.info("Entering into Calculate method input: {}", input);
		double result = 0.0;
		Operator operator = Operator.ADD;
		String[] opreands = null;
		
		/*
		 * If request come for sqrt the we are parsing request and return the response
		 */
		if(input.indexOf('@')>-1)
		{
			String value = input.substring(input.indexOf('@')+1);
			result = value!=" " && value!=null ? Math.sqrt(Double.parseDouble(value)):0;
			prevResults = result;
			return result;
		}
		/*
		 * Other mathematical operation requests continue to parse the input value 
		 */
		if (input.indexOf("+")>0) {
			opreands = input.split("\\+");
			operator = Operator.ADD;
		} else if (input.indexOf("-")>0) {
			opreands = input.split("-");
			operator = Operator.SUBSTRACT;
		} else if (input.indexOf("*")>0) {
			opreands = input.split("\\*");
			operator = Operator.MULTIPLY;
		} else if (input.indexOf("/")>0) {
			opreands = input.split("\\/");
			operator = Operator.DIVIDE;
		}else
		{
			opreands = input.split(" ");
		}

		for (String operand : opreands) {
			double nextOperand = Double.parseDouble(operand);
			if (result == 0.0) {
				result = nextOperand;
				continue;
			}
			switch (operator) {
			case ADD:
				result += nextOperand;
				break;
			case SUBSTRACT:
				result -= nextOperand;
				break;
			case MULTIPLY:
				result *= nextOperand;
				break;
			case DIVIDE:
				result /= nextOperand;
				break;
			default:
				LOGGER.error("Unsupported operation is given for calculation: {}", operator);
				throw new RuntimeException("Unsupported operation is given for calculation");

			}
		}
		prevResults = result;
		return result;

	}
	
	  /**
     * Method used to return the previous calculated result
     * 
     * @return
     */
	@Override
	public double getPreviousValue()
	{
		return prevResults;
	}

	 /**
     * Method used to clear the memory cache
    * @return
     */
	@Override
	public void clearMemory() {
		prevResults=0.0;
	}
	
}
