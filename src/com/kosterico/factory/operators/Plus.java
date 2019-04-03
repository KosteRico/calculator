package com.kosterico.factory.operators;

import com.kosterico.factory.operators.Operation;

public class Plus implements Operation {
    @Override
    public double calculate(double n1, double n2) {

        return n1 + n2;
    }
}
