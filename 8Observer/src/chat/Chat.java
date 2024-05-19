package chat;

import observer.Observer;
import observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class Chat implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    @Override
    public void sendDirectMessage(String message, User toUser) {
        toUser.update(message);
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
