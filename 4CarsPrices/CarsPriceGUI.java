import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class CarsPriceGUI extends JFrame {
    private JComboBox<String> countryComboBox;
    private JComboBox<String> carComboBox;
    private JButton getPriceButton;
    private JLabel priceLabel;

    public CarsPriceGUI() {
        super("Мобильный Магазин");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);

        countryComboBox = new JComboBox<>(new String[]{"BY", "RU"});
        carComboBox = new JComboBox<>(new String[]{"Lada", "Ferrari", "Porshe"});
        getPriceButton = new JButton("Получить цену");
        priceLabel = new JLabel("Цена будет здесь");

        add(countryComboBox);
        add(carComboBox);
        add(getPriceButton);
        add(priceLabel);

        getPriceButton.addActionListener(e -> showPrice());
    }

    private void showPrice() {
        InteAbsFactory factory = countryComboBox.getSelectedItem().equals("BY") ? new ByCarPriceAbsFactory() : new RuCarPriceAbsFactory();
        long price = 0;
        switch (carComboBox.getSelectedItem().toString()) {
            case "Lada":
                price = factory.getLada().getLadaPrice();
                break;
            case "Ferrari":
                price = factory.getFerrari().getFerrariPrice();
                break;
            case "Porshe":
                price = factory.getPorshe().getPorshePrice();
                break;
        }
        priceLabel.setText("Price: " + price);
    }

    public static void main(String[] args) {
        // Используем invokeLater для запуска GUI в потоке диспетчеризации событий
        SwingUtilities.invokeLater(() -> {
            CarsPriceGUI gui = new CarsPriceGUI();
            gui.setVisible(true);
        });
    }
}