
package model;

public class Host extends Person {
    public Host(int id, String name, String phone) {
        super(id, name, phone);
    }

    @Override
    public String toString() {
        return "Host ID: " + getId() + ", Name: " + getName()
                + ", Phone: " + getPhone();
    }
}
