package phones;

public class Iphone implements PhoneShop {
    private String model = "Iphone";
    private int price = 999;

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
        System.out.println("Purchasing an Iphone");
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