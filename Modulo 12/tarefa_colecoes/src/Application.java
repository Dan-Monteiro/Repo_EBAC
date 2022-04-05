import java.util.*;

public class Application {

    private static Scanner scan;

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        subTaskOne();
        subTaskTwo();
    }

    // Parte 1 da atividade do módulo
    public static void subTaskOne() {
        System.out.println("Insira nomes próprios de pessoas separadas por vírgulas ( Ex.: Daniel,Ana,Edson):");
        String[] names = scan.next().split(",");
        Arrays.sort(names);
        for (String name: names ) {
            System.out.println("Nome: " + name);
        }
    }

    // Parte 2 da atividade do módulo
    public static void subTaskTwo() {
        System.out.println("Insira nomes próprios e sexo de pessoas separadas por hífen e cada pessoa separada por vírgula ( Ex.: Daniel-M,Ana-F,Edson-M):");
        String[] persons = scan.next().split(",");
        String [] infoHolder = new String[2];
        String gender = "";
        List<String> malePersons = new ArrayList<>();
        List<String> femalePersons = new ArrayList<>();
        for (String person : persons) {
            infoHolder = person.split("-");
            if (infoHolder.length == 2) {
                gender = infoHolder[1];
                if (gender.toLowerCase(Locale.ROOT).equals("m")) {
                    malePersons.add(infoHolder[0]);
                } else if(gender.toLowerCase(Locale.ROOT).equals("f")) {
                    femalePersons.add(infoHolder[0]);
                }
            }
        }

        Collections.sort(malePersons);
        Collections.sort(femalePersons);
        System.out.println("Lista pessoas sexo masculino: " + malePersons);
        System.out.println("Lista pessoas sexo feminino: " + femalePersons);
    }
}
