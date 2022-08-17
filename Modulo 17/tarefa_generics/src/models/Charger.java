package models;

public class Charger extends Carro{

    private boolean isMuscleCar;

    public Charger(String marca, int motor, String cor) {
        super(marca, motor, cor);
        this.isMuscleCar = true;
    }

    @Override
    public String toString() {
        return "Charger{" +
                "marca='" + marca + '\'' +
                ", motor=" + motor +
                ", cor='" + cor + '\'' +
                ", isMuscleCar=" + isMuscleCar +
                '}';
    }
}
