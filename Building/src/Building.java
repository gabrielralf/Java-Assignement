public abstract class Building {
    protected String name;
    protected String location;
    protected int price;   // monthly rent in EUR
    protected int size;    // square meters

    public Building(String name, String location, int price, int size) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.size = size;
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public int getPrice() { return price; }
    public int getSize() { return size; }

    @Override
    public String toString() {
        return String.format("%s (%s) – €%d, %dm²", name, location, price, size);
    }
}
