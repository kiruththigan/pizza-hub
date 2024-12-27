package models;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusNotifier implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String status;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String status) {
        for (Observer observer : observers) {
            observer.update(status);
        }
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers(status);
    }

    @Override
    public void clearObservers() {
        observers.clear();
    }

}
