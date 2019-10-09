package com.java.calc.common;

import org.springframework.core.convert.converter.Converter;


public class OperatorConverter implements Converter<String, Operator> {

    public Operator convert(String source) {
        return Operator.forValue(source);
    }
}
