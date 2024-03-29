import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DonutFactoryGUI {
    private DonutFactory factory = new DonutFactory();
    private JFrame frame;
    private JButton eatRandomDonutButton;
    private JButton eat100RandomDonutsButton;
    private JButton forgetDonutsButton;
    private JTextArea countTextArea;

    public DonutFactoryGUI() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Donut Factory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        eatRandomDonutButton = new JButton("Eat a Random Donut");
        eatRandomDonutButton.addActionListener(e -> {
            factory.eatRandomDonut();
            updateDonutCounts();
        });

        eat100RandomDonutsButton = new JButton("Eat 100 Random Donuts");
        eat100RandomDonutsButton.addActionListener(e -> {
            factory.eat100RandomDonuts();
            updateDonutCounts();
        });

        forgetDonutsButton = new JButton("Forget Donuts");
        forgetDonutsButton.addActionListener(e -> {
            factory.resetCounts();
            updateDonutCounts();
        });

        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
        buttonPanel.add(eatRandomDonutButton);
        buttonPanel.add(eat100RandomDonutsButton);
        buttonPanel.add(forgetDonutsButton);

        countTextArea = new JTextArea();
        countTextArea.setEditable(false);

        frame.add(new JScrollPane(countTextArea), BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateDonutCounts() {
        String countText = "Cherry Donuts: " + factory.getCherryCount() + "\n" +
                "Chocolate Donuts: " + factory.getChocolateCount() + "\n" +
                "Almond Donuts: " + factory.getAlmondCount();
        countTextArea.setText(countText);
    }

    public void showGUI() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    public static void main(String[] args) {
        DonutFactoryGUI gui = new DonutFactoryGUI();
        gui.showGUI();
    }
}
