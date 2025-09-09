public class CommercialBuilding extends Building {
    private boolean hasParking;
    private int floors;
    private String type; // Office, Retail, Warehouse, ...

    public CommercialBuilding(String name, String location, int price, int size,
                              boolean hasParking, int floors, String type) {
        super(name, location, price, size);
        this.hasParking = hasParking;
        this.floors = floors;
        this.type = type;
    }

    public boolean isHasParking() { return hasParking; }
    public int getFloors() { return floors; }
    public String getType() { return type; }
}
