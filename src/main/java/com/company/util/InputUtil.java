package com.company.util;

import java.io.IOException;
import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static String input(String message, int i){
        if (i == 1) return scanner.nextLine() + "\n" + message;
        else if (i == 2) return message + " : " + scanner.nextLine();
        else return input();
    }

    public static String input(){
        return scanner.nextLine();
    }

    public static int inputInt(){
        return scanner.nextInt();
    }

    public static void clearScreen(){
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
