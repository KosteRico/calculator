package com.kosterico.factory;

import com.kosterico.factory.operators.Operation;

import java.util.List;
import java.util.Stack;

public class Calculator {

    private Calculator () {}

    private static boolean hasComma (String str) {
        return str.indexOf(',') != -1;
    }

    private static double parseWithComma(String str) {
        String res = str.replace(',', '.');
        return Double.parseDouble(res);
    }

/*
    public static void main(String[] args) {
        System.out.println(calculate("3+(4+7)*(-13)"));
    }
*/

    public static String calculate(String exp) {
        List<String> polishNotation = PolishNotator.parse(exp);
        Stack<Double> res = new Stack<>();

        if (polishNotation.get(0).equals(PolishNotator.ERROR_MSG)) {
            return PolishNotator.ERROR_MSG;
        }

        for (String attr : polishNotation) {
            Operation operation;
            if (PolishNotator.isOperation(attr)) {
                if (attr.equals("u-")) {
                    res.push(-res.pop());
                    continue;
                }
                operation = FactoryMain.getOperation(attr);
                double n1 = res.pop();
                double n2 = res.pop();
                res.push(operation.calculate(n2, n1));
            } else if (hasComma(attr)){
                res.push(parseWithComma(attr));
            } else {
                res.push(Double.parseDouble(attr));
            }
        }

        return res.pop().toString();

    }

}
