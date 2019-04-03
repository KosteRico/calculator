package com.kosterico.factory;

import com.kosterico.factory.operators.*;

public class FactoryMain {

    private FactoryMain() {}

    public static Operation getOperation(String operation) {
        switch (operation){
            case "+":
                return new Plus();
            case "-":
                return new Minus();
            case "/":
                return new Divide();
            case "*":
                return new Multiply();
        }

        return null;

    }

}
