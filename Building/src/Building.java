public abstract class Building {
    private final String name;
    private final String city;
    private final String address;
    private final int price; // monthly rent in EUR
    private final int size;  // m²

    protected Building(String name, String city, String address, int price, int size) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.price = price;
        this.size = size;
    }

    public String getName() { return name; }
    public String getCity() { return city; }
    public String getAddress() { return address; }
    public int getPrice() { return price; }
    public int getSize() { return size; }
}
