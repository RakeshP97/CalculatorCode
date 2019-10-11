package com.java.calc.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.calc.common.InputDetails;
import com.java.calc.common.Operator;
import com.java.calc.service.CalculatorService;
import com.java.calc.service.ICalculatorService;
import com.java.calc.service.Response;

import java.math.BigDecimal;
import java.math.MathContext;

@Controller
public class CalculatorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorController.class);

	@Autowired
	private ICalculatorService calculatorService;

	@RequestMapping(value = "/rest/calculate", method = RequestMethod.PUT)
	public @ResponseBody Response calculateResponse(@RequestBody  InputDetails inputDetails) {
		LOGGER.info("Entering getCalculate method with input:"+inputDetails);
		double result = calculatorService.calculate(inputDetails.getFirstNumber(),inputDetails.getSecondNumber(),inputDetails.getOperator());
		Response res = new Response();
		res.setValue(new BigDecimal(result,new MathContext(9)).toString());
		LOGGER.info("Calculated results is:"+res.getValue());
		return res;
	}
	
	
	@RequestMapping(value = "/rest/getPreviousResult", method = RequestMethod.GET)
	public @ResponseBody Response getPreviousResponse() {
		double result = calculatorService.getPreviousValue();
		Response res = new Response();
		res.setValue(new BigDecimal(result,new MathContext(9)).toString());
		LOGGER.info("Previous response is:"+res.getValue());
		return res;
	}
	
	@RequestMapping(value = "/rest/clearMemory", method = RequestMethod.GET)
	public @ResponseBody Response clearMemory() {
		 calculatorService.clearMemory();
		Response res = new Response();
		return res;
	}
}
