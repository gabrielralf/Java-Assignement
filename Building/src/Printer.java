import java.util.List;

public class Printer {
    public static void printResults(List<? extends Building> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("\nNo matches.");
            return;
        }
        for (Building b : list) {
            System.out.println("\n===\"" + b.getName() + "\"===");
            System.out.println("Location: " + b.getLocation());
            System.out.println("Price: " + b.getPrice() + " EUR");
            System.out.println("Size: " + b.getSize() + " m²");

            if (b instanceof ResidentialBuilding) {
                ResidentialBuilding r = (ResidentialBuilding) b;
                System.out.println("PetsAllowed: " + r.isPetsAllowed());
                System.out.println("HasElevator: " + r.isHasElevator());
                System.out.println("Rooms: " + r.getRooms());
            } else if (b instanceof CommercialBuilding) {
                CommercialBuilding c = (CommercialBuilding) b;
                System.out.println("HasParking: " + c.isHasParking());
                System.out.println("Floors: " + c.getFloors());
                System.out.println("Type: " + c.getType());
            }
        }
    }
}
