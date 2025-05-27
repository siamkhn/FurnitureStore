

public class Furniture extends Item {
    private double price;
    private int quantity;
    private String description;

    public Furniture(int id, String name, String category, double price, int quantity, String description) {
        super(id, name, category);
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getDetails() {
        return "Price: " + price + ", Quantity: " + quantity + ", Description: " + description;
    }
}