public abstract class Building {
    String address;
    int floors;
    double area;
    boolean hasElevator;

    public Building(String address, int floors, double area, boolean hasElevator) {
        this.address = address;
        this.floors = floors;
        this.area = area;
        this.hasElevator = hasElevator;
    }

    public abstract double calculateTotalRent();
    

    public void printInfo() {
        System.out.println("Address: " + address);
        System.out.println("Floors: " + floors);
        System.out.println("Area: " + area + " square meters");
        System.out.println("Has Elevator: " + hasElevator);
    }

}

