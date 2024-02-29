import javax.swing.*;

import animals.Animal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton button = new JButton("Click me");
        JTextField textField = new JTextField(20);

        frame.setLayout(new FlowLayout());
        frame.add(textField);
        frame.add(button);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String animalType = textField.getText();
                Animal animalName = Solution.readInitedAnimal(animalType);
                JOptionPane.showMessageDialog(frame, "Animal name: " + animalName.getName());
            }
        });
        
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
