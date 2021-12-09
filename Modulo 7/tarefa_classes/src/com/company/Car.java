package com.company;

/**
 * @author Dan
 * @version 1.1
 * */
public class Car {

    private String brand;
    private String name;
    private float power;
    private float fuel;
    private String color;

    /**
     * Construtor vazio
     * */
    public Car(){}

    /**
     * Construtor com todos os campos
     * @param brand Marca do veículo
     * @param fuel Combustível inicial do veículo
     * @param name Modelo do veículo
     * @param power Potência do motor
     * @param color Cor do carro
     * */
    public Car(String brand, String name, float power, float fuel, String color) {
        this.brand = brand;
        this.name = name;
        this.power = power;
        this.fuel = fuel;
        this.color = color;
    }

    /**
     * Método que inicia o carro e configura marcha inicial
     * */
    public void startEngine() {
        System.out.println("Carro ligando");
        System.out.println("Carro automático");
    }

    /**
     * Método para freiar o carro
     * */
    public void fBreak(){
        System.out.println("Frear carro");
    }

    /**
     * Método para acelerar o carro
     * */
    public void accelerate(){
        System.out.println("Aumentando velocidade");
    }

    /**
     * @deprecated Carros agora são automáticos
     * @see Car#startEngine()
     * */
    public void clutch() {
        System.out.println("Pisando na embreagem");
    }

    /**
     * Método que recupera o valor da quantidade de combustível
     * @return o total de combustível
     * */
    public float checkFuel(){
        return fuel;
    }

    /**
     * Este método consome o combustível do carro
     * decrementa fuel
     * */
    private void consumeFuel(float fuel){
        this.fuel -= fuel;
    }

    /**
     * Este método adiciona fuel(combustível) ao carro
     * incrementa fuel
     * */
    public void addFuel(float fuel){
        this.fuel += fuel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", power=" + power +
                ", fuel=" + fuel +
                ", color='" + color + '\'' +
                '}';
    }
}
