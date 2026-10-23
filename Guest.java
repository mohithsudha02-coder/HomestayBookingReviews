
package model;

public class Guest extends Person {
    public Guest(int id, String name, String phone) {
        super(id, name, phone);
    }

    @Override
    public String toString() {
        return "Guest ID: " + getId() + ", Name: " + getName()
                + ", Phone: " + getPhone();
    }
}
