package src;

import shapes.Circle;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Triangle;
import shapes.decorators.ColoredShapeDecorator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeDemo extends JFrame {
    private Shape circle = new Circle(50, 50, 100);
    private Shape rectangle = new Rectangle(200, 50, 150, 100);
    private Shape triangle = new Triangle(500, 150, 100, 100);

    private Color selectedBorderColor = Color.RED;
    private Color selectedFillColor = Color.WHITE;
    private boolean hasBorder = true;
    private int borderWidth = 2;

    public ShapeDemo() {
        setTitle("Демонстрация Декоратора Фигур");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1));
        JPanel shapePanel = new JPanel();
        JPanel controlPanel1 = new JPanel();
        JPanel controlPanel2 = new JPanel();

        JButton circleButton = new JButton("Круг ");
        JButton rectangleButton = new JButton("Прямоугольник ");
        JButton triangleButton = new JButton("Треугольник ");

        String[] colorOptions = { "Красный", "Зеленый", "Синий", "Желтый", "Черный" };
        JComboBox<String> borderColorComboBox = new JComboBox<>(colorOptions);
        JComboBox<String> fillColorComboBox = new JComboBox<>(colorOptions);
        borderColorComboBox.setSelectedIndex(0);
        fillColorComboBox.setSelectedIndex(1);

        JCheckBox borderCheckBox = new JCheckBox("Показывать Обводку", true);
        JSlider borderWidthSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 2);
        borderWidthSlider.setMajorTickSpacing(1);
        borderWidthSlider.setPaintTicks(true);
        borderWidthSlider.setPaintLabels(true);

        borderColorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) borderColorComboBox.getSelectedItem();
                switch (selected) {
                    case "Красный":
                        selectedBorderColor = Color.RED;
                        break;
                    case "Зеленый":
                        selectedBorderColor = Color.GREEN;
                        break;
                    case "Синий":
                        selectedBorderColor = Color.BLUE;
                        break;
                    case "Желтый":
                        selectedBorderColor = Color.YELLOW;
                        break;
                    case "Черный":
                        selectedBorderColor = Color.BLACK;
                        break;
                }
            }
        });

        fillColorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) fillColorComboBox.getSelectedItem();
                switch (selected) {
                    case "Красный":
                        selectedFillColor = Color.RED;
                        break;
                    case "Зеленый":
                        selectedFillColor = Color.GREEN;
                        break;
                    case "Синий":
                        selectedFillColor = Color.BLUE;
                        break;
                    case "Желтый":
                        selectedFillColor = Color.YELLOW;
                        break;
                    case "Черный":
                        selectedFillColor = Color.BLACK;
                        break;
                }
            }
        });

        borderCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hasBorder = borderCheckBox.isSelected();
            }
        });

        borderWidthSlider.addChangeListener(e -> borderWidth = borderWidthSlider.getValue());

        shapePanel.add(circleButton);
        shapePanel.add(rectangleButton);
        shapePanel.add(triangleButton);

        controlPanel1.add(new JLabel("Цвет Обводки:"));
        controlPanel1.add(borderColorComboBox);
        controlPanel1.add(new JLabel("Цвет Заливки:"));
        controlPanel1.add(fillColorComboBox);
        controlPanel1.add(borderCheckBox);

        controlPanel2.add(new JLabel("Ширина Обводки:"));
        controlPanel2.add(borderWidthSlider);

        panel.add(shapePanel);
        panel.add(controlPanel1);
        panel.add(controlPanel2);

        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                circle = new ColoredShapeDecorator(new Circle(50, 50, 100), selectedBorderColor, selectedFillColor,
                        hasBorder, borderWidth);
                repaint();
            }
        });

        rectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rectangle = new ColoredShapeDecorator(new Rectangle(200, 50, 150, 100), selectedBorderColor,
                        selectedFillColor, hasBorder, borderWidth);
                repaint();
            }
        });

        triangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                triangle = new ColoredShapeDecorator(new Triangle(500, 150, 100, 100), selectedBorderColor,
                        selectedFillColor, hasBorder, borderWidth);
                repaint();
            }
        });

        add(panel, BorderLayout.SOUTH);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        circle.draw(g);
        rectangle.draw(g);
        triangle.draw(g);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShapeDemo demo = new ShapeDemo();
            demo.setVisible(true);
        });
    }
}
