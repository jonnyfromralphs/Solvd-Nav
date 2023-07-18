package com.solvd.view;

import java.util.Scanner;

public class Input {
    private static final Scanner inputScanner = new Scanner(System.in);

    public static int getInt() {
        return Integer.parseInt(inputScanner.nextLine());
    }

    public static double getDouble() {
        return Double.parseDouble(inputScanner.nextLine());
    }

    public static String getString() {
        return inputScanner.nextLine();
    }
}
