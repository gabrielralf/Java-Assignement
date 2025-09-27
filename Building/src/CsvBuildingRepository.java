import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvBuildingRepository implements BuildingRepository {
    private final String path;

    public CsvBuildingRepository(String path) {
        this.path = path;
    }

    @Override
    public List<Building> loadBuildings() throws Exception {
        List<Building> result = new ArrayList<Building>();
        List<String> lines = Files.readAllLines(Paths.get(path));
        for (int i = 1; i < lines.size(); i++) { // skip header
            String line = lines.get(i).trim();
            if (!line.isEmpty()) result.add(parseRowToBuilding(line));
        }
        return result;
    }

    private Building parseRowToBuilding(String line) {
        String[] t = line.split(",", -1);
        String category = t[0].trim();
        String name     = t[1].trim();
        String city     = t[2].trim();
        String address  = t[3].trim();
        int rent        = Integer.parseInt(t[4].trim());
        int size        = Integer.parseInt(t[5].trim());

        if (category.equalsIgnoreCase("Residential")) {
            boolean petsAllowed = parseBool(t[6]);
            boolean hasElevator = parseBool(t[7]);
            int rooms           = Integer.parseInt(t[8].trim());
            int salePrice       = isInt(t[9]) ? Integer.parseInt(t[9].trim()) : rent * 1000;

            return new ResidentialBuilding(name, city, address, rent, size,
                    petsAllowed, hasElevator, rooms, salePrice);

        } else { // Commercial
            boolean hasParking  = parseBool(t[6]);
            int floors          = Integer.parseInt(t[7].trim());
            String type         = t[8].trim();
            int salePerUnit     = isInt(t[9])  ? Integer.parseInt(t[9].trim())  : rent * 900;
            int saleWhole       = isInt(t[10]) ? Integer.parseInt(t[10].trim()) : rent * 20000;

            return new CommercialBuilding(name, city, address, rent, size,
                    hasParking, floors, type, salePerUnit, saleWhole);
        }
    }

    private boolean parseBool(String s) {
        return s != null && s.trim().equalsIgnoreCase("true");
    }

    private boolean isInt(String s) {
        try { Integer.parseInt(s.trim()); return true; } catch (Exception e) { return false; }
    }
}
