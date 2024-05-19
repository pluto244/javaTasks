package observer;

import chat.User;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
    void sendDirectMessage(String message, User toUser);
}
