public class Pessoa {

    private String nome;
    private Sexo sexo;

    public Pessoa(String nome, Sexo sexo) {
        this.nome = nome;
        this.sexo = sexo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", sexo=" + sexo +
                '}';
    }
}
