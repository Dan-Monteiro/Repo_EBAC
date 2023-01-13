package domain.model;

import domain.annotation.ColunaTabela;
import domain.annotation.Tabela;
import domain.annotation.TipoChave;
import domain.generics.Persistente;

import java.time.Instant;

@Tabela("TB_CLIENTE")
public class Cliente implements Persistente {

    @ColunaTabela(dbName = "id", setJavaName = "setId")
    private Long id;

    @TipoChave("getCpf")
    @ColunaTabela(dbName = "CPF", setJavaName = "setCPF")
    private Long CPF;

    @ColunaTabela(dbName = "nome", setJavaName = "setNome")
    private String nome;

    @ColunaTabela(dbName = "telefone", setJavaName = "setTelefone")
    private Long telefone;

    @ColunaTabela(dbName = "endereco", setJavaName = "setEndereco")
    private String endereco;

    @ColunaTabela(dbName = "numero", setJavaName = "setNumero")
    private Integer numero;

    @ColunaTabela(dbName = "cidade", setJavaName = "setCidade")
    private String cidade;

    @ColunaTabela(dbName = "estado", setJavaName = "setEstado")
    private String estado;

    @ColunaTabela(dbName = "data_cadastro", setJavaName = "setDataCadastro")
    private Instant dataCadastro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCPF() {
        return CPF;
    }

    public void setCPF(Long CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Instant getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Instant dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
