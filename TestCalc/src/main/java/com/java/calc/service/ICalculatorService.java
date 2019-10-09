package com.java.calc.service;


public interface ICalculatorService {

    /**
     * Method is used for calculation for given input by parsing the input
     *
     * @param input
     * @return
     */
   public double calculate(String input);
    
    /**
     * Method used to get the previous results
     * 
     * @return
     */
    public double getPreviousValue();
    
    /**
     * Method used to clear the memory cache
    * @return
     */
   public void clearMemory();

}
