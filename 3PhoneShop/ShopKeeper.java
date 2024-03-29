
import phones.PhoneShop;
import phones.Iphone;
import phones.Samsung;
import phones.Blackberry;

class ShopKeeper {
    private PhoneShop iphone;
    private PhoneShop samsung;
    private PhoneShop blackberry;

    public ShopKeeper() {
        iphone = new Iphone();
        samsung = new Samsung();
        blackberry = new Blackberry();
    }

    public String purchaseIphone() {
        iphone.purchase();
        return "Model: " + ((Iphone) iphone).getModel() + "\nPrice: $" + ((Iphone) iphone).getPrice();
    }

    public String purchaseSamsung() {
        samsung.purchase();
        return "Model: " + ((Samsung) samsung).getModel() + "\nPrice: $" + ((Samsung) samsung).getPrice();
    }

    public String purchaseBlackberry() {
        blackberry.purchase();
        return "Model: " + ((Blackberry) blackberry).getModel() + "\nPrice: $" + ((Blackberry) blackberry).getPrice();
    }
}
