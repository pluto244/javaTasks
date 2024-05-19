package src;

import javax.swing.JOptionPane;

public class RealDoor implements Door {
    private String doorCode;
    private boolean open;

    public RealDoor(String doorCode) {
        this.doorCode = doorCode;
        this.open = false;
    }

    @Override
    public void open() {
        if (open) {
            notifyUser("Дверь уже открыта!");
        } else {
            open = true;
            notifyUser("Дверь открыта!");
        }
    }

    @Override
    public void close() {
        if (!open) {
            notifyUser("Дверь уже закрыта!");
        } else {
            open = false;
            notifyUser("Дверь закрыта!");
        }
    }

    @Override
    public boolean isOpen() {
        return open;
    }

    public String getDoorCode() {
        return doorCode;
    }

    private void notifyUser(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}