package seminars.first.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertTrue;
import static seminars.first.model.Calculator.calculatingDiscount;

import java.sql.SQLOutput;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {


    public static int calculation(int firstOperand, int secondOperand, char operator) {
        int result;

        switch (operator) {
            case '+':
                result = firstOperand + secondOperand;
                break;
            case '-':
                result = firstOperand - secondOperand;
                break;
            case '*':
                result = firstOperand * secondOperand;
                break;
            case '/':
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                    break;
                } else {
                    throw new ArithmeticException("Division by zero is not possible");
                }
            default:
                throw new IllegalStateException("Unexpected value operator: " + operator);
        }
        return result;
    }

    public static double squareRootExtraction(double number) {
        if (number == 0) {
            throw new ArithmeticException("It is not possible to extract the root from 0");
        }
        if (number < 0) {
            throw new ArithmeticException("It is impossible to extract the root from negative numbers");
        }

        double t;
        double squareRoot = number / 2;
        do {
            t = squareRoot;
            squareRoot = (t + (number / t)) / 2;
        }
        while ((t - squareRoot) != 0);
        return squareRoot;

        // или просто return Math.sqrt(number);
    }


    /**
     * @param purchaseAmount сумма покупки
     * @param discountAmount размер скидки
     * @return возвращает сумму покупки с учетом скидки
     */
    public static double calculatingDiscount(double purchaseAmount, int discountAmount) {

        return purchaseAmount - (purchaseAmount * discountAmount / 100);

    }
    public static void calculatingDiscountCheck(double purchaseAmount, int discountAmount) {

        assertThatThrownBy(() ->
                calculatingDiscount(purchaseAmount, discountAmount)
        ).isInstanceOf(IllegalStateException.class);

        assertThat(purchaseAmount).isInstanceOf(Double.class);
        assertThat(discountAmount).isInstanceOf(Integer.class);
        assertThat(purchaseAmount).isGreaterThan(0).as("Сумма должна быть больше нуля");
        assertThat(discountAmount).isBetween(0,99).as("Скидка должна быть от ноля до ста");

    }




}