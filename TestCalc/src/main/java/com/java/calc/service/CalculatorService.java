package com.java.calc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.java.calc.common.Operator;

@Service
public class CalculatorService implements ICalculatorService {

	private double prevResults = 0.0;

	private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorService.class);

	/**
	 * Method is used for calculation based on input given and return the results
	 *
	 * @param firstNumber
	 * @param secondNumber
	 * @param operator
	 * 
	 * @return
	 */
	@Override
	public double calculate(final String firstNumber, final String secondNumber, final String operator) {

		LOGGER.info("Entering into Calculate method firstNumber: {} and secondNumber {} and operator {}", firstNumber,secondNumber,operator);
		double result = 0.0;
		@SuppressWarnings("static-access")
		Operator operator1 = Operator.ADD.forValue(operator);
		switch (operator1) {
		case ADD:
			result = Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber);
			break;
		case SUBSTRACT:
			result = Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber);
			break;
		case MULTIPLY:
			result = Double.parseDouble(firstNumber) * Double.parseDouble(secondNumber);
			break;
		case DIVIDE:
			if (secondNumber != null && secondNumber != "" && Double.parseDouble(secondNumber) > 0) {
				result = Double.parseDouble(firstNumber) / Double.parseDouble(secondNumber);
			} else {
				throw new RuntimeException("Unsupported operation is given for calculation");
			}
			break;
		case SQRT:
			result = firstNumber != " " && firstNumber != null ? Math.sqrt(Double.parseDouble(firstNumber)) : 0;
			break;
		default:
			LOGGER.error("Unsupported operation is given for calculation: {}", operator);
			throw new RuntimeException("Unsupported operation is given for calculation");

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
	public double getPreviousValue() {
		return prevResults;
	}

	/**
	 * Method used to clear the memory cache
	 * 
	 * @return
	 */
	@Override
	public void clearMemory() {
		prevResults = 0.0;
	}

}
