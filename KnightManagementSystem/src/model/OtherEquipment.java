package model;

public class OtherEquipment extends Equipment {
    private static final long serialVersionUID = 1L;

    public OtherEquipment(String name, double weight, double price) {
        super(name, weight, price, EquipmentType.OTHER);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}