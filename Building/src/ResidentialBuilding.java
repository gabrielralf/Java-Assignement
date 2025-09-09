public class ResidentialBuilding extends Building {
    private boolean petsAllowed;
    private boolean hasElevator;
    private int rooms;

    public ResidentialBuilding(String name, String location, int price, int size,
                               boolean petsAllowed, boolean hasElevator, int rooms) {
        super(name, location, price, size);
        this.petsAllowed = petsAllowed;
        this.hasElevator = hasElevator;
        this.rooms = rooms;
    }

    public boolean isPetsAllowed() { return petsAllowed; }
    public boolean isHasElevator() { return hasElevator; }
    public int getRooms() { return rooms; }
}
