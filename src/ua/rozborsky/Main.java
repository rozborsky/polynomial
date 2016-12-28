package ua.rozborsky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by roman on 28.12.2016.
 */
public class Main {
    private static BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        System.out.println("Please insert values");
        int a = getValue("a");
        int b = getValue("b");
        int n = getValue("n");

        System.out.println("\n");
        System.out.println("a = " + a + "; b = " + b + "; n = " + n + ";");

        String equation = createStringEquation(n, getCoefficients(n));
        int result = getResult(a, b, n);
        System.out.println(equation + "=" + result);
    }


    private static int getValue(String member) {
        int value = 0;
        boolean correctValue = false;

        while (!correctValue) {
            System.out.println("insert " + member);

            try {
                String stringValue = bufferReader.readLine();
                value = Integer.valueOf(stringValue);
                correctValue = true;
            } catch (IOException e) {
                System.out.println("can't read value\n");
            } catch (NumberFormatException e) {
                System.out.println("wrong format " + member + "\n");
            }
        }

        return value;
    }


    private static int[] getCoefficients(int degree) {
        int coefficients[] = new int[degree + 1];

        for (int i = 1; i <= degree; i++) {
            coefficients[i] = 0;
        }

        coefficients[0] = 1;
        for (int j = 1; j <= degree; j++){
            for (int i = j; i >= 1; i--) {
                coefficients[i] = coefficients[i - 1] + coefficients[i];
            }
        }

        return coefficients;
    }


    private static int getResult(int a, int b, int n) {

        return (int)Math.pow((a + b), n);
    }


    private static String createStringEquation(int n, int[] coefficients) {
        String [] memberEquation = new String[coefficients.length];
        String equation = "";

        for (int i = 0; i < memberEquation.length; i++) {
            String member = "";

            if (coefficients[i] != 1) {
                member += coefficients[i];
            }

            member += addA(i, n, memberEquation.length);
            member += addB(i, memberEquation.length);

            if (i != (memberEquation.length - 1)) {
                member += "+";
            }
            equation += member;
        }

        return equation;
    }

    private static String addA(int i, int n, int numberOfMembers){
        String a = "";

        if (i != (numberOfMembers - 1)) {
            if (i != 0) {
                a += "*";
            }
            a += "a";
            if ((n - i) != 1) {
                a +=  "^" + (n - i);
            }
        }

        return a;
    }

    private static String addB(int i, int numberOfMembers) {
        String b = "";

        if (i != 0) {
            if (i != (numberOfMembers - 1)) {
                b += "*";
            }
            b += "b";
            if (i != 1) {
                b += "^" + i;
            }
        }

        return b;
    }
}
