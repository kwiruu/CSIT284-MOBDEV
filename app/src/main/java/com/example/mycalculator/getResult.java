package com.example.mycalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class getResult {
    public final Postfix converter;
    public final sequential seqConverter;
    public final com.example.mycalculator.evaluator evaluator;

    public getResult() {
        converter = new Postfix();
        seqConverter = new sequential();
        evaluator = new evaluator();
    }

    public String calculate(String mathExpression) {
        ArrayList < String > postfixExpression = converter.convertToPostfix(mathExpression);
        double result;

        try {
            result = evaluator.evaluate(postfixExpression);
        } catch (ArithmeticException ae) {
            return "";
        } catch (IllegalArgumentException ae) {
            return "";
        }

        if (result - Math.floor(result) > 0)
            return Double.toString(result);
        return Integer.toString((int) result);
    }

    public String sequentially(String mathExpression) {
        ArrayList < String > postfixExpression = seqConverter.convertSequentially(mathExpression);
        double result;

        try {
            result = evaluator.evaluate(postfixExpression);
        } catch (ArithmeticException ae) {
            return "Invalid";
        } catch (IllegalArgumentException ae) {
            return "Error";
        }

        if (result - Math.floor(result) > 0)
            return Double.toString(result);
        return Integer.toString((int) result);
    }
}
class Postfix {
    private final Stack < Character > operatorStack = new Stack < > ();

    private final Stack < Boolean > doParenthesisMultiply = new Stack < > ();
    private final Stack < Boolean > doParenthesisHaveSigns = new Stack < > ();
    private final Stack < String > parenthesisSigns = new Stack < > ();

    private final ArrayList < String > unarySigns = new ArrayList < > ();
    private final ArrayList < String > queueOutput = new ArrayList < > ();

    private final Map < Character, Integer > precedencers = new HashMap < > ();

    public Postfix() {
        precedencers.put('+', 1);
        precedencers.put('-', 1);
        precedencers.put('*', 2);
        precedencers.put('/', 2);
        precedencers.put('^', 3);
    }

    public ArrayList < String > convertToPostfix(String infixExpression) {

        operatorStack.clear();
        doParenthesisMultiply.clear();
        doParenthesisHaveSigns.clear();
        parenthesisSigns.clear();
        unarySigns.clear();
        queueOutput.clear();

        infixExpression = infixExpression.replace(" ", "");
        boolean numberHasSigns = false;
        int unaryCount = 0;

        for (int i = 0; i < infixExpression.length(); i++) {
            Character token = infixExpression.charAt(i);
            char prevToken = (i > 0) ? infixExpression.charAt(i - 1) : ' ';

            if (token == '(') {
                operatorStack.push(token);

                if (unaryCount > 0) {
                    doParenthesisHaveSigns.push(true);
                    moveUnarySignsToParenthesisSigns();
                    unaryCount = 0;
                } else {
                    doParenthesisHaveSigns.push(false);
                }

                boolean isParenthesisMultiply = prevToken == ')';
                doParenthesisMultiply.push(isParenthesisMultiply);
            }

            else if (token == ')') {
                boolean parenthesisHasSigns = doParenthesisHaveSigns.pop();

                if (numberHasSigns && !parenthesisHasSigns) {
                    moveUnarySignsToOutput();
                    numberHasSigns = false;
                }

                while (!operatorStack.isEmpty() && operatorStack.peek() != '(')
                    queueOutput.add(String.valueOf(operatorStack.pop()));
                operatorStack.pop();

                if (parenthesisHasSigns)
                    moveParenthesisSignsToOutput();

                if (doParenthesisMultiply.pop())
                    queueOutput.add("*");
            }

            else if (Character.isDigit(token) || token == '.') {

                if (unaryCount > 0) {
                    numberHasSigns = true;
                    unaryCount = 0;
                }

                if (Character.isDigit(prevToken) || prevToken == '.')
                    queueOutput.add(queueOutput.remove(queueOutput.size() - 1) + token);

                else
                    queueOutput.add(String.valueOf(token));
            }

            else {

                if ((precedencers.containsKey(prevToken) || prevToken == ' ' || prevToken == '(') &&
                        (token == '-' || token == '+')) {
                    unaryCount++;
                    if (token == '-')
                        unarySigns.add("-1");
                    else
                        unarySigns.add("1");
                    continue;
                }

                if (numberHasSigns) {
                    moveUnarySignsToOutput();
                    numberHasSigns = false;
                }

                while (!operatorStack.isEmpty()) {
                    int stackPrecedence;
                    int tokenPrecedence;

                    try {
                        stackPrecedence = precedencers.getOrDefault(operatorStack.peek(), 0);
                        tokenPrecedence = precedencers.get(token);
                    } catch (NullPointerException ne) {
                        break;
                    }

                    if (stackPrecedence < tokenPrecedence) break;

                    queueOutput.add(String.valueOf(operatorStack.pop()));
                }

                operatorStack.push(token);
            }
        }

        if (numberHasSigns)
            moveUnarySignsToOutput();

        while (!operatorStack.isEmpty())
            queueOutput.add(String.valueOf(operatorStack.pop()));

        return new ArrayList < > (queueOutput);
    }

    private void moveUnarySignsToOutput() {
        int signsCount = unarySigns.size();
        for (int j = 0; j < signsCount; j++) {
            queueOutput.add(unarySigns.remove(0));
            queueOutput.add("*");
        }
    }

    private void moveUnarySignsToParenthesisSigns() {

        parenthesisSigns.push(",");

        Collections.reverse(unarySigns);

        int signsCount = unarySigns.size();
        for (int j = 0; j < signsCount; j++)
            parenthesisSigns.push(unarySigns.remove(0));
    }

    private void moveParenthesisSignsToOutput() {
        System.out.println(parenthesisSigns);
        while (!Objects.equals(parenthesisSigns.peek(), ",")) {
            queueOutput.add(parenthesisSigns.pop());
            queueOutput.add("*");
        }

        parenthesisSigns.pop();
    }

}
class evaluator {
    private final Stack < Double > valueStack = new Stack < > ();

    public double evaluate(ArrayList < String > postfixExpression)
            throws ArithmeticException, IllegalArgumentException {
        if (postfixExpression == null || postfixExpression.isEmpty()) {
            throw new IllegalArgumentException("Empty or null postfix expression.");
        }

        valueStack.clear();

        for (String term: postfixExpression) {
            if (ifOperator(term)) {
                try {
                    double b = valueStack.pop();
                    double a = valueStack.pop();
                    double result = useOperator(a, b, term);
                    valueStack.push(result);
                } catch (EmptyStackException e) {
                    throw new IllegalArgumentException("Malformed postfix expression: not enough operands for operator.");
                }
            } else {
                try {
                    double value = Double.parseDouble(term);
                    valueStack.push(value);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Malformed postfix expression: invalid term encountered.");
                }
            }
        }

        if (valueStack.size() != 1) {
            throw new IllegalArgumentException("Malformed postfix expression: too many operands.");
        }

        return valueStack.peek();
    }

    private static boolean ifOperator(String term) {
        return term.equals("+") || term.equals("-") || term.equals("*") || term.equals("/") ||
                term.equals("^") || term.equals("x") || term.equals("/");
    }

    private static double useOperator(double a, double b, String operator) throws ArithmeticException {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
            case "x":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Division by zero.");
                }
                return a / b;
            case "^":
                return Math.pow(a, b);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
class sequential {
    private final Stack < Character > operatorStack = new Stack < > ();

    private final Stack < Boolean > doParenthesisMultiply = new Stack < > ();
    private final Stack < Boolean > doParenthesisHaveSigns = new Stack < > ();
    private final Stack < String > parenthesisSigns = new Stack < > ();

    private final ArrayList < String > unarySigns = new ArrayList < > ();
    private final ArrayList < String > outputQueue = new ArrayList < > ();

    private final Map < Character, Integer > precedence = new HashMap < > ();

    public sequential() {
        precedence.put('+', 1);
        precedence.put('-', 1);
        precedence.put('*', 1);
        precedence.put('/', 1);
        precedence.put('^', 1);
    }

    public ArrayList < String > convertSequentially(String infixExpression) {

        operatorStack.clear();
        doParenthesisMultiply.clear();
        doParenthesisHaveSigns.clear();
        parenthesisSigns.clear();
        unarySigns.clear();
        outputQueue.clear();

        infixExpression = infixExpression.replace(" ", "");
        boolean numberHasSigns = false;
        int unaryCount = 0;

        for (int i = 0; i < infixExpression.length(); i++) {
            Character token = infixExpression.charAt(i);
            char prevToken = (i > 0) ? infixExpression.charAt(i - 1) : ' ';

            if (token == '(') {
                operatorStack.push(token);

                if (unaryCount > 0) {
                    doParenthesisHaveSigns.push(true);
                    moveUnarySignsToParenthesisSigns();
                    unaryCount = 0;
                } else {
                    doParenthesisHaveSigns.push(false);
                }

                boolean isParenthesisMultiply = prevToken == ')';
                doParenthesisMultiply.push(isParenthesisMultiply);
            }

            else if (token == ')') {
                boolean parenthesisHasSigns = doParenthesisHaveSigns.pop();

                if (numberHasSigns && !parenthesisHasSigns) {
                    moveUnarySignsToOutput();
                    numberHasSigns = false;
                }

                while (!operatorStack.isEmpty() && operatorStack.peek() != '(')
                    outputQueue.add(String.valueOf(operatorStack.pop()));
                operatorStack.pop();

                if (parenthesisHasSigns)
                    moveParenthesisSignsToOutput();

                if (doParenthesisMultiply.pop())
                    outputQueue.add("*");
            }

            else if (Character.isDigit(token) || token == '.') {

                if (unaryCount > 0) {
                    numberHasSigns = true;
                    unaryCount = 0;
                }

                if (Character.isDigit(prevToken) || prevToken == '.')
                    outputQueue.add(outputQueue.remove(outputQueue.size() - 1) + token);

                else
                    outputQueue.add(String.valueOf(token));
            }

            else {

                if ((precedence.containsKey(prevToken) || prevToken == ' ' || prevToken == '(') &&
                        (token == '-' || token == '+')) {
                    unaryCount++;
                    if (token == '-')
                        unarySigns.add("-1");
                    else
                        unarySigns.add("1");
                    continue;
                }

                if (numberHasSigns) {
                    moveUnarySignsToOutput();
                    numberHasSigns = false;
                }

                if (!operatorStack.isEmpty())
                    outputQueue.add(String.valueOf(operatorStack.pop()));
                operatorStack.push(token);
            }
        }

        if (numberHasSigns)
            moveUnarySignsToOutput();

        while (!operatorStack.isEmpty())
            outputQueue.add(String.valueOf(operatorStack.pop()));

        return new ArrayList < > (outputQueue);
    }

    private void moveUnarySignsToOutput() {
        int signsCount = unarySigns.size();
        for (int j = 0; j < signsCount; j++) {
            outputQueue.add(unarySigns.remove(0));
            outputQueue.add("*");
        }
    }

    private void moveUnarySignsToParenthesisSigns() {

        parenthesisSigns.push(",");

        Collections.reverse(unarySigns);

        int signsCount = unarySigns.size();
        for (int j = 0; j < signsCount; j++)
            parenthesisSigns.push(unarySigns.remove(0));
    }

    private void moveParenthesisSignsToOutput() {
        System.out.println(parenthesisSigns);
        while (!Objects.equals(parenthesisSigns.peek(), ",")) {
            outputQueue.add(parenthesisSigns.pop());
            outputQueue.add("*");
        }

        parenthesisSigns.pop();
    }
}

class Splitter {
    private static final ArrayList < String > outputQueue = new ArrayList < > ();

    public static ArrayList < String > getTermsList(String infixExpression) {

        outputQueue.clear();
        infixExpression = infixExpression.replace(" ", "");

        for (int i = 0; i < infixExpression.length(); i++) {
            Character token = infixExpression.charAt(i);
            char prevToken = (i > 0) ? infixExpression.charAt(i - 1) : ' ';

            if (Character.isDigit(token) || token == '.') {

                if (Character.isDigit(prevToken) || prevToken == '.')
                    outputQueue.add(outputQueue.remove(outputQueue.size() - 1) + token);

                else
                    outputQueue.add(String.valueOf(token));
            }

            else {
                outputQueue.add(String.valueOf(token));
            }
        }

        return new ArrayList < > (outputQueue);
    }
}