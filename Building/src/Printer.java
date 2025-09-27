import java.util.List;

public class Printer {
    public static void printResults(List<? extends Building> list) {
        if (list == null || list.isEmpty()) return;

        for (Building b : list) {
            System.out.println("\n===\"" + b.getName() + "\"===");
            System.out.println("Location: " + b.getCity() + ",  " + b.getAddress());
            System.out.println("Size: " + b.getSize() + " square meters");
            System.out.println("Rent (per month): " + b.getPrice() + " EUR");

            if (b instanceof ResidentialBuilding) {
                ResidentialBuilding r = (ResidentialBuilding) b;
                System.out.println("Pets Allowed: " + yesNo(r.isPetsAllowed()));
                System.out.println("Has Elevator: " + yesNo(r.isHasElevator()));
                System.out.println("Rooms: " + r.getRooms());
                System.out.println("Sale Price (apartment): " + r.getSalePrice() + " EUR");
            } else if (b instanceof CommercialBuilding) {
                CommercialBuilding c = (CommercialBuilding) b;
                System.out.println("Type: " + c.getType());
                System.out.println("Has Parking: " + yesNo(c.isHasParking()));
                System.out.println("Floors: " + c.getFloors());
                System.out.println("Sale Price (per unit): " + c.getSalePricePerUnit() + " EUR");
                System.out.println("Sale Price (whole building): " + c.getSalePriceWhole() + " EUR");
            }
        }
    }

    private static String yesNo(boolean b) { return b ? "Yes" : "No"; }
}
