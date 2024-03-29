package phones;

public class Samsung implements PhoneShop {
    private String model = "Samsung";
    private int price = 899;

    @Override
    public void modelNo() {
        System.out.println("Model number: " + model);
    }

    @Override
    public void price() {
        System.out.println("Price: $" + price);
    }

    @Override
    public void purchase() {
        System.out.println("Purchasing a Samsung");
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getModel() {
        return model;
    }
}