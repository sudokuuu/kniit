package org.kniit.patterns;

import java.util.ArrayList;
import java.util.List;

public class ObserverExample {

    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        Observer currentDisplay = new CurrentConditionsDisplay();
        Observer avgDisplay     = new AverageTemperatureDisplay();

        station.addObserver(currentDisplay);
        station.addObserver(avgDisplay);

        station.setMeasurements(20.5f, 65f);
        station.setMeasurements(22.0f, 70f);
        station.setMeasurements(18.3f, 60f);

        station.removeObserver(currentDisplay);

        station.setMeasurements(25.0f, 55f);
    }
}

interface Observer {
    void update(float temperature, float humidity);
}

interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

class WeatherStation implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private float temperature;
    private float humidity;

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature, humidity);
        }
    }

    public void setMeasurements(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        notifyObservers(); // оповещаем всех подписчиков
    }
}

class CurrentConditionsDisplay implements Observer {
    @Override
    public void update(float temperature, float humidity) {
        System.out.println("Текущие условия: " + temperature + "C, влажность " + humidity + "%");
    }
}

class AverageTemperatureDisplay implements Observer {
    private float sum = 0;
    private int count = 0;

    @Override
    public void update(float temperature, float humidity) {
        sum += temperature;
        count++;
        System.out.println("Средняя температура за " + count + " измерений: " + (sum / count) + "C");
    }
}
