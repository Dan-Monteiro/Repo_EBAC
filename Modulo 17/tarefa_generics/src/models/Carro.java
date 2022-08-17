package models;

public abstract class Carro {

    protected String marca;
    protected int motor;
    protected String cor;

    public Carro(String marca, int motor, String cor) {
        this.marca = marca;
        this.motor = motor;
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "marca='" + marca + '\'' +
                ", motor=" + motor +
                ", cor='" + cor + '\'' +
                '}';
    }
}
