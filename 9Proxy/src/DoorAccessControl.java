package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoorAccessControl extends JFrame {
    private ProxyDoor proxyDoor;
    private JTextField codeField;
    private JLabel doorLabel;
    private Timer openTimer;
    private Timer closeTimer;
    private ImageIcon[] openImages;
    private ImageIcon[] closeImages;
    private int animationIndex;
    private boolean isAnimating;

    public DoorAccessControl() {
        RealDoor realDoor = new RealDoor("1234");
        proxyDoor = new ProxyDoor(realDoor);

        setTitle("Доступ к двери");
        setSize(700, 400); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Введите код: ");
        codeField = new JTextField(10);
        JButton openButton = new JButton("Открыть дверь");
        JButton closeButton = new JButton("Закрыть дверь");
        doorLabel = new JLabel();

        openImages = new ImageIcon[] {
                loadImage("images/door_closed.png"),
                loadImage("images/door_opening_1.png"),
                loadImage("images/door_opening_2.png"),
                loadImage("images/door_open.png")
        };

        closeImages = new ImageIcon[] {
                loadImage("images/door_open.png"),
                loadImage("images/door_opening_2.png"),
                loadImage("images/door_opening_1.png"),
                loadImage("images/door_closed.png")
        };

        doorLabel.setIcon(openImages[0]);

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredCode = codeField.getText();
                proxyDoor.setEnteredCode(enteredCode);
                if (proxyDoor.checkCredentials()) {
                    if (!proxyDoor.isOpen() && !isAnimating) {
                        proxyDoor.open();
                        startOpenAnimation();
                    } else {
                        JOptionPane.showMessageDialog(null, "Дверь уже открыта.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Неверный код.");
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (proxyDoor.isOpen() && !isAnimating) {
                    proxyDoor.close();
                    startCloseAnimation();
                } else {
                    JOptionPane.showMessageDialog(null, "Дверь уже закрыта.");
                }
            }
        });

        JButton cardButton = new JButton("Эмуляция карты");

        cardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proxyDoor.setCardTouchSimulation("CARD");
                if (proxyDoor.checkCredentials()) {
                    if (!proxyDoor.isOpen() && !isAnimating) {
                        proxyDoor.open();
                        startOpenAnimation();
                    } else {
                        JOptionPane.showMessageDialog(null, "Дверь уже открыта.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Неверная карта.");
                }
            }
        });

        panel.add(label);
        panel.add(codeField);
        panel.add(openButton);
        panel.add(closeButton);
        panel.add(cardButton);
        panel.add(doorLabel);

        add(panel);

        openTimer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (animationIndex < openImages.length) {
                    doorLabel.setIcon(openImages[animationIndex]);
                    animationIndex++;
                } else {
                    openTimer.stop();
                    isAnimating = false;
                }
            }
        });

        closeTimer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (animationIndex < closeImages.length) {
                    doorLabel.setIcon(closeImages[animationIndex]);
                    animationIndex++;
                } else {
                    closeTimer.stop();
                    isAnimating = false;
                }
            }
        });
    }

    private void startOpenAnimation() {
        animationIndex = 0;
        isAnimating = true;
        openTimer.start();
    }

    private void startCloseAnimation() {
        animationIndex = 0;
        isAnimating = true;
        closeTimer.start();
    }

    private ImageIcon loadImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(600, 300, Image.SCALE_SMOOTH); // Обновлено для изменения размера
        return new ImageIcon(scaledImg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DoorAccessControl().setVisible(true);
            }
        });
    }
}
