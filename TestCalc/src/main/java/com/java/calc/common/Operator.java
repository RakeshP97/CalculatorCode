package com.java.calc.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Operator {

    ADD("+"),
    SUBSTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    SQRT("@");

    private String sign;

    private Operator(String sign) {
        this.sign = sign;
    }

    @JsonValue
    public String toValue() {
        return sign;
    }

    @JsonCreator
    public static Operator forValue(String value) {
        return Arrays.stream(values())
                .filter(operator -> operator.name().equals(value))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unsupported Operation given " + value));
    }

}
