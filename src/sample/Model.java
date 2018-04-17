package sample;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Model {

    private double result;
    private String pattern;

    public Model() {

        this.pattern = "#.######";
    }

    public void calculation(String expression) {

        String[] arguments = expression.split(" ");

        double firstNumber  = Double.parseDouble(arguments[0]);
        char operator       = arguments[1].charAt(0);
        double secondNumber = Double.parseDouble(arguments[2]);

        if (secondNumber == 0.0 && operator == '/')
            throw new ArithmeticException("Деление на ноль!");

        switch (operator) {
            case '*': this.result = firstNumber * secondNumber; return;
            case '/': this.result = firstNumber / secondNumber; return;
            case '-': this.result = firstNumber - secondNumber; return;
            case '%': this.result = firstNumber % secondNumber; return;
            case '+': this.result = firstNumber + secondNumber; return;
            default: throw new ArithmeticException("Неизвестная операция!");
        }
    }

    public String getResult() {

        NumberFormat newFormat = new DecimalFormat(this.pattern);

        return newFormat.format(this.result);
    }
}
