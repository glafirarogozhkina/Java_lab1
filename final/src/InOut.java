package io;

import java.util.Scanner;
public class InOut {
    public static void Out(String str) {
        System.out.println(str);
    }
    public static double In(String str) {
        Scanner in = new Scanner(System.in);
        System.out.print(str);
        return in.nextDouble();
    }
    public static void Err(String e) {
        System.err.println(e);
    }
}