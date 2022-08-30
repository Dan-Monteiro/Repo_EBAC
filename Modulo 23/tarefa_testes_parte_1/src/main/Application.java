package main;

import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {

        Scanner scan = new Scanner(System.in);
        List<Pessoa> listPessoas = new ArrayList<Pessoa>();
        boolean primeiraExecucao = true;
        int quantidade = 0;
        String entrada = "";
        String nome;
        String sexo;
        Sexo sexoEnum;

        if (primeiraExecucao) {
            System.out.println("====================================");
        }
        System.out.println("Informe a quantidade de pessoas a serem cadastradas:");
        try {
            quantidade = scan.nextInt();
            primeiraExecucao = false;
        } catch (InputMismatchException ex) {
            System.out.println("Entrada inválida!");
            menu();
        }

        for (int i = 0; i < quantidade; i++) {

            boolean mustRepeatStep = true;

            do {
                System.out.println("Informe o nome e o sexo da pessoa separados por vírgula \",\":");
                entrada = scan.next();
                String[] dados = entrada.split(",");

                if (dados.length != 2) {
                    System.out.println("Dados não correspondem a entrada esperada!");
                }

                nome = dados[0].trim();
                sexo = dados[1].trim();

                for (Sexo sexoValue : Sexo.values()) {
                    if (sexoValue.name().equalsIgnoreCase(sexo)) {
                        mustRepeatStep = false;
                        break;
                    }
                }

                if (mustRepeatStep) {
                    System.out.println("Dados não correspondem a entrada esperada, tente novamente!");
                    System.out.println("Dica! tente informar um dos seguintes sexos:");
                    Arrays.stream(Sexo.values()).forEach(s -> System.out.println("- " + s));
                }

            } while (mustRepeatStep);

            if (sexo.equalsIgnoreCase(String.valueOf(Sexo.FEMININO))) {
                sexoEnum = Sexo.FEMININO;
            } else {
                sexoEnum = Sexo.MASCULINO;
            }
            listPessoas.add(new Pessoa(nome, sexoEnum));
        }

        List<Pessoa> listPessoasFemininas = getListaPessoasFemininas(listPessoas);

        if (listPessoasFemininas.isEmpty()) {
            System.out.println("Não foram encontradas pessoas femininas");
            menu();
        }
        listPessoasFemininas.forEach(System.out::println);
        menu();
    }

    public static List<Pessoa> getListaPessoasFemininas(List<Pessoa> listaPessoas) {
        return listaPessoas.stream()
                .filter(p -> p.getSexo().equals(Sexo.FEMININO))
                .collect(Collectors.toList());
    }
}
