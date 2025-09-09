public class CommercialBuilding extends Building {
    String businessType;
    int numOffices;
    boolean hasConferenceRoom;
    double officeArea;

    public CommercialBuilding(String address, int floors, double area, boolean hasElevator,
                              String businessType, int numOffices, boolean hasConferenceRoom, double officeArea) {
        super(address, floors, area, hasElevator); // Call to the superclass constructor
        this.businessType = businessType;
        this.numOffices = numOffices;
        this.hasConferenceRoom = hasConferenceRoom;
        this.officeArea = officeArea;
    }

    @Override
    public double calculateTotalRent() {
        double baseRentPerSqM = 20.0; // Example base rent per square meter
        return officeArea * baseRentPerSqM;
}
    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Business Type: " + businessType);
        System.out.println("Number of Offices: " + numOffices);
        System.out.println("Has Conference Room: " + hasConferenceRoom);
        System.out.println("Office Area: " + officeArea + " square meters");
        System.out.println("Rent per Square Meter: € 20.0" );
        System.out.println("Total Rent: € " + calculateTotalRent());
    }
}
