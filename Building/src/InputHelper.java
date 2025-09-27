import java.util.Scanner;

public class InputHelper {

    // --- Enums used by BuildingManager ---
    public enum TriBool { YES, NO, ANY }
    public enum Transaction { RENT, BUY }
    public enum OfficeBuyScope { UNIT, WHOLE }

    // --- High-level prompts ---
    public static Transaction readTransaction(Scanner sc) {
        while (true) {
            System.out.print("Are you looking to rent or to buy? [rent/buy]: ");
            String s = sc.nextLine().trim().toLowerCase();
            if (s.startsWith("r")) return Transaction.RENT;
            if (s.startsWith("b")) return Transaction.BUY;
            System.out.println("Please enter 'rent' or 'buy'.");
        }
    }

    public static OfficeBuyScope readOfficeBuyScope(Scanner sc) {
        while (true) {
            System.out.print("For offices: buy the whole building or a single floor/unit? [whole/unit]: ");
            String s = sc.nextLine().trim().toLowerCase();
            if (s.startsWith("w")) return OfficeBuyScope.WHOLE;
            if (s.startsWith("u") || s.startsWith("f")) return OfficeBuyScope.UNIT; // unit/floor
            System.out.println("Please enter 'whole' or 'unit'.");
        }
    }

    // --- Yes/No/Any prompts ---
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

    // --- Primitive prompts ---
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
}
