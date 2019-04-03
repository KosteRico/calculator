package com.kosterico.factory;

import java.util.*;

public class PolishNotator {

    public static final String ERROR_MSG = "Invalid expression!";

    private static final String operations = "*/+-";

    private static final String delimiters = operations + "()";

    private PolishNotator() {}

    public static boolean isDelimiter(String str) {
        if (str.length() != 1) return false;
        for (char c : delimiters.toCharArray()) {
            if (str.charAt(0) == c) return true;
        }
        return false;
    }

    public static boolean isOperation(String str) {
        if (str.equals("u-")) return true;
        for (char c : operations.toCharArray()) {
            if (str.charAt(0) == c) {
                return true;
            }
        }
        return false;
    }

    private static int priority(String str) {
        char c = str.charAt(0);
        if (c == '(') return 1;
        if (c == '+' || c == '-') return 2;
        if (c == '*' || c == '/') return 3;
        return 4;
    }

/*    public static void main(String[] args) {
        System.out.println(parse("3+445*-125").toString());
    }*/

    public static ArrayList<String> parse(String expression) {
        StringTokenizer tokenizer = new StringTokenizer(expression, delimiters, true);
        ArrayList<String> parsedExp = new ArrayList<>(tokenizer.countTokens());
        Stack<String> operationsStack = new Stack<>();
        String current = "";
        String prev = "";

        try {
            while (tokenizer.hasMoreTokens()) {
                current = tokenizer.nextToken();
                if (current.equals(" ")) continue;
                if (isDelimiter(current)) {
                    if (current.equals("(")) operationsStack.push(current);
                    else if (current.equals(")")) {
                        while (!operationsStack.peek().equals("(")) {
                            parsedExp.add(operationsStack.pop());
                        }
                        operationsStack.pop();
                    } else {
                        if (current.equals("-") && prev.equals("") || (isDelimiter(prev) && !prev.equals(")"))) {
                            current = "u-";
                        } else if (isOperation(current)) {
                            while (!operationsStack.isEmpty() && (priority(current) <= priority(operationsStack.peek()))) {
                                parsedExp.add(operationsStack.pop());
                            }
                        }
                        operationsStack.push(current);
                    }
                } else {
                    parsedExp.add(current);
                }
                prev = current;
            }

            while (!operationsStack.isEmpty()) {
                String str = operationsStack.pop();
                if (!isOperation(str)) {
                    throw new Exception();
                }
                if (isOperation(str)) parsedExp.add(str);
            }
        } catch (Exception e) {
            parsedExp.add(0, ERROR_MSG);
            return parsedExp;
        }
        return parsedExp;
    }
}
