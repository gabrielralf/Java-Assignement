public class ResidentialBuilding extends Building {
    int numApartments;
    boolean hasParking;
    boolean allowPets;

    public ResidentialBuilding(String address, int floors, double area, boolean hasElevator,
                               int numApartments, boolean hasParking, boolean allowPets) {
        super(address, floors, area, hasElevator); // Call to the superclass constructor
        this.numApartments = numApartments;
        this.hasParking = hasParking;
        this.allowPets = allowPets;
    }

    @Override
    public double calculateTotalRent() {
        double baseRentPerSqM = 15.0; // Example base rent per square meter
        return area * baseRentPerSqM;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Number of Apartments: " + numApartments);
        System.out.println("Has Parking: " + hasParking);
        System.out.println("Allows Pets: " + allowPets);
        System.out.println("Rent per Square Meter: € 15.0" );
        System.out.println("Total Rent: € " + calculateTotalRent());
    }
}
