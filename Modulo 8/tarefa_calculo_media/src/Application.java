
public class Application {

    public static void main(String[] args) {
       double nota1 = Double.parseDouble(args[0]);
       double nota2 = Double.parseDouble(args[1]);
       double nota3 = Double.parseDouble(args[2]);
       double nota4 = Double.parseDouble(args[3]);

       double media  = (nota1 + nota2 + nota3 + nota4)/ 4;

        System.out.println("A média das notas é: " + media);
    }
}
