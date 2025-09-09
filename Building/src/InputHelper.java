import java.util.Scanner;

public class InputHelper {
    public enum TriBool { YES, NO, ANY }

    public static TriBool readTriBool(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim().toLowerCase();
            if (s.isEmpty() || s.startsWith("a")) return TriBool.ANY;
            if (s.startsWith("y") || "true".equals(s)) return TriBool.YES;
            if (s.startsWith("n") || "false".equals(s)) return TriBool.NO;
            System.out.println("Please enter yes/no/any.");
        }
    }

    public static boolean matchTriBool(TriBool t, boolean value) {
        if (t == TriBool.ANY) return true;
        if (t == TriBool.YES) return value;
        return !value; // NO
    }

    public static int readInt(Scanner sc, String prompt, int defaultVal) {
        System.out.print(prompt);
        String s = sc.nextLine().trim();
        if (s.isEmpty()) return defaultVal;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("Not a number. Using default.");
            return defaultVal;
        }
    }

    public static String readString(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    public static boolean inRange(int x, int min, int max) {
        return x >= min && x <= max;
    }
}
