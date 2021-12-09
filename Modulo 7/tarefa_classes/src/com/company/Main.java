package com.company;

public class Main {

    public static void main(String[] args) {
        Car car = new Car("RR", "New CaRR", 1.0F, 20, "Brown");
        car.startEngine();
        car.clutch();
        car.checkFuel();
        System.out.println(car.toString());
    }
}
