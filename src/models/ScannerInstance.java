package models;

import java.util.Scanner;

public class ScannerInstance {
    private static volatile Scanner scanner;

    private ScannerInstance() {
    }

    public static Scanner getInstance() {
        if (scanner == null) {
            synchronized (ScannerInstance.class) {
                if (scanner == null) {
                    scanner = new Scanner(System.in);
                }
            }
        }
        return scanner;
    }

    public static void closeScanner() {
        synchronized (ScannerInstance.class) {
            if (scanner != null) {
                scanner.close();
                scanner = null;
            }
        }
    }
}
