import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuildingManager {
    private final List<Building> allBuildings;

    public BuildingManager(BuildingRepository repository) throws Exception {
        this.allBuildings = repository.loadBuildings();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Real Estate 4 You! Where you can find what you are looking for.");
        sleepMillis(2000);
        System.out.println("Next, answer some questions so we can tailor the results as you wish.\n");

        while (true) {
            System.out.println("Choose building type [residential/commercial]:");
            String typeInput = sc.nextLine().trim().toLowerCase();

            boolean validType = false;
            boolean foundAny = false;

            if (typeInput.startsWith("r")) {
                validType = true;
                foundAny = handleResidential(sc);
            } else if (typeInput.startsWith("c")) {
                validType = true;
                foundAny = handleCommercial(sc);
            } else {
                System.out.println("Invalid type. Use 'residential' or 'commercial'.\n");
            }

            if (!validType) continue;
            if (foundAny) break;

            System.out.println("\nNo matches found. Try different filters? [y/n]");
            String again = sc.nextLine().trim().toLowerCase();
            if (!again.startsWith("y")) {
                System.out.println("Goodbye!");
                break;
            }
            System.out.println();
        }
    }

    // Residential 
    // Returns true if at least one match was printed
    private boolean handleResidential(Scanner sc) {
        // Rent or Buy?
        InputHelper.Transaction txn = InputHelper.readTransaction(sc);

        // Common filters
        int minSize  = InputHelper.readInt(sc, "Min size (square meters, blank for none): ", Integer.MIN_VALUE);
        InputHelper.TriBool pets     = InputHelper.readTriBool(sc, "Pets allowed? [yes/no/any]: ");
        InputHelper.TriBool elevator = InputHelper.readTriBool(sc, "Elevator? [yes/no/any]: ");
        int minRooms = InputHelper.readInt(sc, "Min rooms (blank for none): ", Integer.MIN_VALUE);

        List<ResidentialBuilding> results = new ArrayList<ResidentialBuilding>();

        if (txn == InputHelper.Transaction.RENT) {
            int maxRent = InputHelper.readInt(sc, "Max monthly rent (EUR, blank for none): ", Integer.MAX_VALUE);

            for (Building b : allBuildings) {
                if (b instanceof ResidentialBuilding) {
                    ResidentialBuilding r = (ResidentialBuilding) b;

                    if (r.getPrice() > maxRent) continue;           // monthly rent
                    if (r.getSize() < minSize) continue;
                    if (!InputHelper.matchTriBool(pets, r.isPetsAllowed())) continue;
                    if (!InputHelper.matchTriBool(elevator, r.isHasElevator())) continue;
                    if (r.getRooms() < minRooms) continue;

                    results.add(r);
                }
            }
        } else { // BUY
            int maxBuy = InputHelper.readInt(sc, "Max total purchase price (EUR, blank for none): ", Integer.MAX_VALUE);

            for (Building b : allBuildings) {
                if (b instanceof ResidentialBuilding) {
                    ResidentialBuilding r = (ResidentialBuilding) b;

                    if (r.getSalePrice() > maxBuy) continue;        // apartment sale price
                    if (r.getSize() < minSize) continue;
                    if (!InputHelper.matchTriBool(pets, r.isPetsAllowed())) continue;
                    if (!InputHelper.matchTriBool(elevator, r.isHasElevator())) continue;
                    if (r.getRooms() < minRooms) continue;

                    results.add(r);
                }
            }
        }
        System.out.println("\nThank you for your input! We will find the best result for you. This might take a bit...");
        sleepMillis(3000); // 3 seconds
        Printer.printResults(results);
        return !results.isEmpty();
    }

    // Commercial 
    // Returns true if at least one match was printed
    private boolean handleCommercial(Scanner sc) {
        // Rent or Buy?
        InputHelper.Transaction txn = InputHelper.readTransaction(sc);

        int minSize  = InputHelper.readInt(sc, "Min size (square meters, blank for none): ", Integer.MIN_VALUE);
        InputHelper.TriBool parking = InputHelper.readTriBool(sc, "Parking? [yes/no/any]: ");
        int minFloors = InputHelper.readInt(sc, "Min floors (blank for none): ", Integer.MIN_VALUE);
        String typeFilter = InputHelper.readString(sc, "Type (Office/Retail/Warehouse/etc., blank for any): ").trim();

        List<CommercialBuilding> results = new ArrayList<CommercialBuilding>();

        if (txn == InputHelper.Transaction.RENT) {
            int maxRent = InputHelper.readInt(sc, "Max monthly rent (EUR, blank for none): ", Integer.MAX_VALUE);

            for (Building b : allBuildings) {
                if (b instanceof CommercialBuilding) {
                    CommercialBuilding c = (CommercialBuilding) b;

                    if (c.getPrice() > maxRent) continue;           // monthly rent
                    if (c.getSize() < minSize) continue;
                    if (!InputHelper.matchTriBool(parking, c.isHasParking())) continue;
                    if (c.getFloors() < minFloors) continue;
                    if (!typeFilter.isEmpty() && !c.getType().equalsIgnoreCase(typeFilter)) continue;

                    results.add(c);
                }
            }
        } else { // BUY
            // For offices, ask whether buying a UNIT or the WHOLE building
            boolean officeMode = typeFilter.equalsIgnoreCase("Office") ||
                                 typeFilter.equalsIgnoreCase("Offices") ||
                                 typeFilter.isEmpty();
            InputHelper.OfficeBuyScope scope = InputHelper.OfficeBuyScope.UNIT;
            if (officeMode) {
                scope = InputHelper.readOfficeBuyScope(sc);
            }

            int maxBuy = InputHelper.readInt(sc, "Max total purchase price (EUR, blank for none): ", Integer.MAX_VALUE);

            for (Building b : allBuildings) {
                if (b instanceof CommercialBuilding) {
                    CommercialBuilding c = (CommercialBuilding) b;

                    if (c.getSize() < minSize) continue;
                    if (!InputHelper.matchTriBool(parking, c.isHasParking())) continue;
                    if (c.getFloors() < minFloors) continue;
                    if (!typeFilter.isEmpty() && !c.getType().equalsIgnoreCase(typeFilter)) continue;

                    int priceToCheck;
                    if (c.getType().equalsIgnoreCase("Office")) {
                        priceToCheck = (scope == InputHelper.OfficeBuyScope.WHOLE)
                                ? c.getSalePriceWhole()
                                : c.getSalePricePerUnit();
                    } else {
                        // Non-office: treat purchase as a per-unit asset
                        priceToCheck = c.getSalePricePerUnit();
                    }

                    if (priceToCheck > maxBuy) continue;

                    results.add(c);
                }
            }
        }
        System.out.println("\nThank you for your input! We will find the best result for you. This might take a bit...");
        sleepMillis(3000); // 3 seconds
        Printer.printResults(results);
        return !results.isEmpty();
    }
    // =================== helpers ===================
    private void sleepMillis(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}