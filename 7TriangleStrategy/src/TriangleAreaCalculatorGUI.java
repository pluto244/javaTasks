import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TriangleAreaCalculatorGUI extends JFrame {
    private TriangleAreaCalculator calculator = new TriangleAreaCalculator();

    // UI Components
    private JComboBox<String> strategySelector;
    private JPanel inputPanel;
    private JLabel labelA, labelB, labelC;
    private JTextField inputA, inputB, inputC;
    private JLabel resultLabel;
    private JLabel instructionLabel;

    public TriangleAreaCalculatorGUI() {
        initUI();
        setTitle("Triangle Area Calculator");
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initUI() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        instructionLabel = new JLabel("Select a calculation method");
        add(instructionLabel);

        strategySelector = new JComboBox<>(
                new String[] { "Select Method", "Vertex Coordinates", "Heron's Formula", "Base-Height" });
        strategySelector.addActionListener(this::onStrategyChanged);
        add(strategySelector);

        inputPanel = new JPanel();
        inputA = new JTextField(10);
        inputB = new JTextField(10);
        inputC = new JTextField(10);
        inputA.setVisible(false);
        inputB.setVisible(false);
        inputC.setVisible(false);
        labelA = new JLabel("");
        labelB = new JLabel("Choose a method of triangle area calculator");
        labelC = new JLabel("");

        inputPanel.add(labelA);
        inputPanel.add(inputA);
        inputPanel.add(labelB);
        inputPanel.add(inputB);
        inputPanel.add(labelC);
        inputPanel.add(inputC);
        add(inputPanel);

        // Setup calculate button with action listener to calculate area
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this::calculateArea);
        resultLabel = new JLabel("Area: ");
        add(resultLabel);
        add(calculateButton);

    }

    private void onStrategyChanged(ActionEvent e) {
        // Change visibility of input fields based on selected method
        String selectedMethod = (String) strategySelector.getSelectedItem();
        inputA.setText("");
        inputB.setText("");
        inputA.setText("");
        if ("Vertex Coordinates".equals(selectedMethod)) {
            inputA.setVisible(true);
            inputB.setVisible(true);
            inputC.setVisible(true);

            inputA.setToolTipText("write two numbers separated by a space");
            labelA.setText("coordinates for vertex A x1 y1");

            inputB.setToolTipText("write two numbers separated by a space");
            labelB.setText("coordinates for vertex B x2 y2");

            inputC.setToolTipText("write two numbers separated by a space");
            labelC.setText("coordinates for vertex C  x3 y3");

        } else if ("Heron's Formula".equals(selectedMethod)) {
            inputA.setVisible(true);
            inputB.setVisible(true);
            inputC.setVisible(true);

            inputA.setToolTipText("Enter length of side A");
            labelA.setText("length of side A");
            inputB.setToolTipText("Enter length of side B");
            labelB.setText("length of side B");
            inputC.setToolTipText("Enter length of side C");
            labelC.setText("length of side C");

        } else if ("Base-Height".equals(selectedMethod)) {
            inputA.setVisible(true);
            inputB.setVisible(true);
            inputC.setVisible(false);

            inputA.setToolTipText("Enter base length");
            labelA.setText("length of base");
            inputB.setToolTipText("Enter height");
            labelB.setText("length of height");
            inputC.setToolTipText("");
            labelC.setText("");

        } else {
            inputA.setVisible(false);
            inputB.setVisible(false);
            inputC.setVisible(false);

            labelA.setText("");
            labelB.setText("Select a calculation method");
            labelC.setText("");

        }
        revalidate();
        repaint();
    }

    private void calculateArea(ActionEvent e) {
        try {
            String strategy = (String) strategySelector.getSelectedItem();
            if ("Vertex Coordinates".equals(strategy)) {
                Point a = parsePoint(inputA.getText());
                Point b = parsePoint(inputB.getText());
                Point c = parsePoint(inputC.getText());
                calculator.setStrategy(new VertexCoordinatesStrategy(a, b, c));
            } else if ("Heron's Formula".equals(strategy)) {
                double sideA = parseDouble(inputA.getText());
                double sideB = parseDouble(inputB.getText());
                double sideC = parseDouble(inputC.getText());
                calculator.setStrategy(new HeronFormulaStrategy(sideA, sideB, sideC));
            } else if ("Base-Height".equals(strategy)) {
                double base = parseDouble(inputA.getText());
                double height = parseDouble(inputB.getText());
                calculator.setStrategy(new BaseHeightStrategy(base, height));
            } else {
                JOptionPane.showMessageDialog(this, "Please select a calculation method.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double area = calculator.calculateArea();
            resultLabel.setText("Area: " + area);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Point parsePoint(String text) throws IllegalArgumentException {
        String[] parts = text.split(" ");
        if (parts.length != 2) throw new IllegalArgumentException("Please enter exactly two numbers separated by a space.");
        try {
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            return new Point(x, y);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Please enter valid integer coordinates.");
        }
    }
    
    private double parseDouble(String text) throws NumberFormatException, IllegalArgumentException {
        try {
            double value = Double.parseDouble(text);
            if (value <= 0) {
                throw new IllegalArgumentException("Please enter a positive number.");
            }
            return value;
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("Please enter a valid number.");
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TriangleAreaCalculatorGUI ex = new TriangleAreaCalculatorGUI();
            ex.setVisible(true);
        });
    }
}