package chat;

import observer.Observer;

import javax.swing.DefaultListModel;

public class User implements Observer {
    private final String name;
    private Chat chat;
    private DefaultListModel<String> messagesModel;

    public User(String name) {
        this.name = name;
        this.messagesModel = new DefaultListModel<>();
    }

    public void joinChat(Chat chat) {
        this.chat = chat;
        chat.addObserver(this);
        chat.notifyObservers(name + " joined the chat.");
    }

    public void leaveChat() {
        if (chat != null) {
            chat.notifyObservers(name + " left the chat.");
            chat.removeObserver(this);
            chat = null;
        }
    }

    public void sendMessage(String message) {
        if (chat != null) {
            chat.notifyObservers(name + ": " + message);
        }
    }

    public void sendDirectMessage(String message, User toUser) {
        if (chat != null) {
            chat.sendDirectMessage(name + " (private): " + message, toUser);
            messagesModel.addElement(name + " to " + toUser.getName() + " (private): " + message);
        }
    }

    @Override
    public void update(String message) {
        messagesModel.addElement(message);
    }

    public String getName() {
        return name;
    }

    public DefaultListModel<String> getMessagesModel() {
        return messagesModel;
    }
}
