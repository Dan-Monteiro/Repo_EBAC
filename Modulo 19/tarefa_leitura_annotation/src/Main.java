import java.lang.annotation.Annotation;

public class Main {

    public static void main(String[] args) {
        Class<Entidade> classEntidade = Entidade.class;
        Tabela annotation = classEntidade.getAnnotation(Tabela.class);
        String nomeTabela = annotation.nome();
        System.out.println(String.format("O nome da tabela da entidade é: %s", nomeTabela));
    }
}
