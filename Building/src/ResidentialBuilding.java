public final class ResidentialBuilding extends Building {
    private final boolean petsAllowed;
    private final boolean hasElevator;
    private final int rooms;
    private final int salePrice; // total price to buy the apartment

    public ResidentialBuilding(String name, String city, String address, int rentPrice, int size,
                               boolean petsAllowed, boolean hasElevator, int rooms,
                               int salePrice) {
        super(name, city, address, rentPrice, size);
        this.petsAllowed = petsAllowed;
        this.hasElevator = hasElevator;
        this.rooms = rooms;
        this.salePrice = salePrice;
    }

    public boolean isPetsAllowed() { return petsAllowed; }
    public boolean isHasElevator() { return hasElevator; }
    public int getRooms() { return rooms; }
    public int getSalePrice() { return salePrice; }
}
