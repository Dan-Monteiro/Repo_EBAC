package models;

public class Beatle extends Carro{

    private String tipoPlaca;

    public Beatle(String marca, int motor, String cor, String tipoPlaca) {
        super(marca, motor, cor);
        this.tipoPlaca = tipoPlaca;
    }

    @Override
    public String toString() {
        return "Beatle{" +
                "tipoPlaca='" + tipoPlaca + '\'' +
                ", marca='" + marca + '\'' +
                ", motor=" + motor +
                ", cor='" + cor + '\'' +
                '}';
    }
}
