import javax.swing.*;
import java.awt.*;

public class PhoneShopGUI {
    private JFrame frame;
    private JButton btnPurchaseIphone;
    private JButton btnPurchaseSamsung;
    private JButton btnPurchaseBlackberry;
    private ShopKeeper shopKeeper;

    public PhoneShopGUI() {
        shopKeeper = new ShopKeeper();
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Phone Shop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);
        frame.setLayout(new FlowLayout());

        btnPurchaseIphone = new JButton("Purchase Iphone");
        btnPurchaseSamsung = new JButton("Purchase Samsung");
        btnPurchaseBlackberry = new JButton("Purchase Blackberry");

        frame.add(btnPurchaseIphone);
        frame.add(btnPurchaseSamsung);
        frame.add(btnPurchaseBlackberry);

        btnPurchaseIphone.addActionListener(e -> showConfirmation("Iphone", shopKeeper.purchaseIphone()));
        btnPurchaseSamsung.addActionListener(e -> showConfirmation("Samsung", shopKeeper.purchaseSamsung()));
        btnPurchaseBlackberry.addActionListener(e -> showConfirmation("Blackberry", shopKeeper.purchaseBlackberry()));
    }

    private void showConfirmation(String model, String purchaseDetails) {
        JOptionPane.showMessageDialog(frame,
                "Your order for a " + model + " has been successfully placed. " + purchaseDetails,
                "Purchase Confirmation",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void showGUI() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PhoneShopGUI gui = new PhoneShopGUI();
            gui.showGUI();
        });
    }
}
