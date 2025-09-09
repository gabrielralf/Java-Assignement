import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuildingManager {
    private final List<Building> allBuildings;

    public BuildingManager(String csvPath) throws IOException {
        this.allBuildings = loadBuildingsFromCsv(csvPath);
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose building type [residential/commercial]:");
        String typeInput = sc.nextLine().trim().toLowerCase();

        if (typeInput.startsWith("r")) {
            handleResidential(sc);
        } else if (typeInput.startsWith("c")) {
            handleCommercial(sc);
        } else {
            System.out.println("Invalid type.");
        }
    }

    // --------- Residential ----------
    private void handleResidential(Scanner sc) {
        int minPrice = InputHelper.readInt(sc, "Min price (EUR, blank for none): ", Integer.MIN_VALUE);
        int maxPrice = InputHelper.readInt(sc, "Max price (EUR, blank for none): ", Integer.MAX_VALUE);
        int minSize  = InputHelper.readInt(sc, "Min size (m², blank for none): ", Integer.MIN_VALUE);

        InputHelper.TriBool pets     = InputHelper.readTriBool(sc, "Pets allowed? [yes/no/any]: ");
        InputHelper.TriBool elevator = InputHelper.readTriBool(sc, "Elevator? [yes/no/any]: ");
        int minRooms = InputHelper.readInt(sc, "Min rooms (blank for none): ", Integer.MIN_VALUE);

        List<ResidentialBuilding> results = new ArrayList<ResidentialBuilding>();
        for (Building b : allBuildings) {
            if (b instanceof ResidentialBuilding) {
                ResidentialBuilding r = (ResidentialBuilding) b;

                if (!InputHelper.inRange(r.getPrice(), minPrice, maxPrice)) continue;
                if (r.getSize() < minSize) continue;
                if (!InputHelper.matchTriBool(pets, r.isPetsAllowed())) continue;
                if (!InputHelper.matchTriBool(elevator, r.isHasElevator())) continue;
                if (r.getRooms() < minRooms) continue;

                results.add(r);
            }
        }
        Printer.printResults(results);
    }

    // --------- Commercial ----------
    private void handleCommercial(Scanner sc) {
        int minPrice = InputHelper.readInt(sc, "Min price (EUR, blank for none): ", Integer.MIN_VALUE);
        int maxPrice = InputHelper.readInt(sc, "Max price (EUR, blank for none): ", Integer.MAX_VALUE);
        int minSize  = InputHelper.readInt(sc, "Min size (m², blank for none): ", Integer.MIN_VALUE);

        InputHelper.TriBool parking = InputHelper.readTriBool(sc, "Parking? [yes/no/any]: ");
        int minFloors = InputHelper.readInt(sc, "Min floors (blank for none): ", Integer.MIN_VALUE);
        String typeFilter = InputHelper.readString(sc, "Type (Office/Retail/Warehouse/etc., blank for any): ").trim();

        List<CommercialBuilding> results = new ArrayList<CommercialBuilding>();
        for (Building b : allBuildings) {
            if (b instanceof CommercialBuilding) {
                CommercialBuilding c = (CommercialBuilding) b;

                if (!InputHelper.inRange(c.getPrice(), minPrice, maxPrice)) continue;
                if (c.getSize() < minSize) continue;
                if (!InputHelper.matchTriBool(parking, c.isHasParking())) continue;
                if (c.getFloors() < minFloors) continue;
                if (!typeFilter.isEmpty() && !c.getType().equalsIgnoreCase(typeFilter)) continue;

                results.add(c);
            }
        }
        Printer.printResults(results);
    }

    // --------- CSV loader ----------
    private List<Building> loadBuildingsFromCsv(String path) throws IOException {
        List<Building> result = new ArrayList<Building>();
        List<String> lines = Files.readAllLines(Paths.get(path));
        for (int i = 1; i < lines.size(); i++) { // skip header
            String line = lines.get(i).trim();
            if (!line.isEmpty()) {
                result.add(parseRowToBuilding(line));
            }
        }
        return result;
    }

    private Building parseRowToBuilding(String line) {
        // CSV columns:
        // 0:Category 1:Name 2:Location 3:Price 4:Size 5:Extra1 6:Extra2 7:Extra3
        String[] t = line.split(",", -1);
        String category = t[0].trim();
        String name     = t[1].trim();
        String location = t[2].trim();
        int price       = Integer.parseInt(t[3].trim());
        int size        = Integer.parseInt(t[4].trim());

        if (category.equalsIgnoreCase("Residential")) {
            boolean petsAllowed = parseBool(t[5]);
            boolean hasElevator = parseBool(t[6]);
            int rooms           = Integer.parseInt(t[7].trim());
            return new ResidentialBuilding(name, location, price, size, petsAllowed, hasElevator, rooms);
        } else {
            boolean hasParking  = parseBool(t[5]);
            int floors          = Integer.parseInt(t[6].trim());
            String type         = t[7].trim();
            return new CommercialBuilding(name, location, price, size, hasParking, floors, type);
        }
    }

    private boolean parseBool(String s) {
        return s != null && s.trim().equalsIgnoreCase("true");
    }
}
