package src;

import javax.swing.JOptionPane;

public class ProxyDoor implements Door {
    private RealDoor realDoor;
    private String enteredCode;
    private String cardTouchSimulation;
    private final String correctCode = "1234";

    public ProxyDoor(RealDoor realDoor) {
        this.realDoor = realDoor;
    }

    public void setEnteredCode(String enteredCode) {
        this.enteredCode = enteredCode;
    }

    public void setCardTouchSimulation(String cardTouchSimulation) {
        this.cardTouchSimulation = cardTouchSimulation;
    }

    @Override
    public void open() {
        if (realDoor.isOpen()) {
            notifyUser("Дверь уже открыта!");
        } else {
            realDoor.open();
            clearCredentials();
        }
    }

    @Override
    public void close() {
        if (!realDoor.isOpen()) {
            notifyUser("Дверь уже закрыта!");
        } else {
            realDoor.close();
            clearCredentials();
        }
    }

    @Override
    public boolean isOpen() {
        return realDoor.isOpen();
    }

    public boolean checkCredentials() {
        return (enteredCode != null && enteredCode.equals(correctCode)) ||
               (cardTouchSimulation != null && cardTouchSimulation.equals("CARD"));
    }

    private void clearCredentials() {
        this.enteredCode = null;
        this.cardTouchSimulation = null;
    }

    private void notifyUser(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
