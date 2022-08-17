import models.Beatle;
import models.Carro;
import models.Charger;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Carro> listaCarros;

    public static void main(String[] args) {
        listaCarros = new ArrayList<>();
        listaCarros.add(new Beatle("Volkswagen", 1100, "bege", "placa preta"));
        listaCarros.add(new Beatle("Volkswagen", 1300, "vermelho", "placa padrão"));
        listaCarros.add(new Carro("Honda", 2000, "prata") {
            @Override
            public String toString() {
                return super.toString();
            }
        });
        listaCarros.add(new Charger("Dodge", 8000, "preto"));
        for (Carro carro: listaCarros) {
            System.out.println(carro);
        }
    }
}
