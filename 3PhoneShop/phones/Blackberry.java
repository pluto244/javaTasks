
package phones;

public class Blackberry implements PhoneShop {
    private String model = "Blackberry";
    private int price = 499;

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
        System.out.println("Purchasing a Blackberry");
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