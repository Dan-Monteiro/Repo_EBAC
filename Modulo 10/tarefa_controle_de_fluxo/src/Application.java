import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        int quantidade_notas = 4;
        int soma_notas = 0;
        int media_final = 0;
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < quantidade_notas; i++) {
            System.out.printf("Informe a %d° nota: ", i + 1);
            soma_notas += scan.nextInt();
        }

        media_final = soma_notas / quantidade_notas;

        if (media_final >= 7) {
            System.out.println("Aprovado!");
        } else if (media_final >= 5) {
            System.out.println("Recuperação!");
        } else {
            System.out.println("Reprovado!");
        }
    }
}
