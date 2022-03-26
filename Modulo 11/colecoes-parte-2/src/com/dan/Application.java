package com.dan;

import java.util.*;

public class Application {

    private static String [] lista_sexo;

    public static void main(String[] args) {

        List<Pessoa> lista_masculino = new ArrayList<>();
        List<Pessoa> lista_feminino = new LinkedList<>();

        lista_sexo = new String [] {
                "masculino",
                "feminino"
        };

        int quantidade = 5;

        Scanner scan = new Scanner(System.in);

        String nome = "";
        String sexo = "";

        for(int i = 0; i < quantidade; i++) {

           if(i == 0) {
               System.out.println("Informe o nome da pessoa:");
           } else {
               System.out.println("Informe o nome da nova pessoa:");
           }

           nome = scan.next();

           do {
               if(i == 0) {
                   System.out.println("Informe o sexo da pessoa:");
               } else {
                   System.out.println("Informe o sexo da nova pessoa:");
               }
               sexo = scan.next();

               if(!verificaListaSexo(sexo)) {
                   System.out.println("Você deve informar um dos seguintes valores:");
                   for (String _sexo: lista_sexo) {
                       System.out.println("- " + _sexo);
                   }
               }

           }while (!verificaListaSexo(sexo));

           if(sexo.equals(lista_sexo[0])) {
               lista_masculino.add(new Pessoa(nome, sexo));
           } else {
               lista_feminino.add(new Pessoa(nome, sexo));
           }

        }

        if(!lista_masculino.isEmpty()) {
            System.out.println(lista_masculino);
        }

        if(!lista_feminino.isEmpty()) {
            System.out.println(lista_feminino);
        }
    }

    private static boolean verificaListaSexo(String sexo) {
        for (String _sexo: lista_sexo) {
            if(_sexo.toLowerCase(Locale.ROOT).equals(sexo.toLowerCase(Locale.ROOT))) {
                return true;
            }
        }
        return false;
    }
}
