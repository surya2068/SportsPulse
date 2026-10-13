package sportspulse;

import java.util.Scanner;

public final class Input {
    private Input() {}
    public static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Enter a whole number."); }
        }
    }
    public static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Double.parseDouble(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Enter a valid number."); }
        }
    }
    public static String readText(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}
