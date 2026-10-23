
package model;

public class Listing {
    private int id;
    private String name;
    private String location;
    private double price;
    private Host host;

    public Listing(int id, String name, String location,
                   double price, Host host) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.host = host;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public double getPrice() { return price; }
    public Host getHost() { return host; }

    @Override
    public String toString() {
        return "Listing ID: " + id + ", Name: " + name
                + ", Location: " + location
                + ", Price/Night: Rs." + price
                + ", Host: " + host.getName();
    }
}
