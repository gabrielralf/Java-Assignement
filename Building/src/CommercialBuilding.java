public final class CommercialBuilding extends Building {
    private final boolean hasParking;
    private final int floors;
    private final String type; // Office, Retail, Warehouse, ...
    private final int salePricePerUnit; // buy a single floor/unit
    private final int salePriceWhole;   // buy the entire building

    public CommercialBuilding(String name, String city, String address, int rentPrice, int size,
                              boolean hasParking, int floors, String type,
                              int salePricePerUnit, int salePriceWhole) {
        super(name, city, address, rentPrice, size);
        this.hasParking = hasParking;
        this.floors = floors;
        this.type = type;
        this.salePricePerUnit = salePricePerUnit;
        this.salePriceWhole = salePriceWhole;
    }

    public boolean isHasParking() { return hasParking; }
    public int getFloors() { return floors; }
    public String getType() { return type; }
    public int getSalePricePerUnit() { return salePricePerUnit; }
    public int getSalePriceWhole() { return salePriceWhole; }
}
