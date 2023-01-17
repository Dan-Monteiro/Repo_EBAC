package domain.model;

import domain.annotation.ColunaTabela;
import domain.annotation.Tabela;
import domain.annotation.TipoChave;
import domain.generics.Persistente;

@Tabela("TB_ESTOQUE_PRODUTO")
public class EstoqueProduto implements Persistente {

    @ColunaTabela(dbName = "id", setJavaName = "setId")
    private Long id;

    @TipoChave("getIdProduto")
    @ColunaTabela(dbName = "id_produto_fk", setJavaName = "setIdProdutoFk")
    private Long idProdutoFk;

    private Produto produto;

    @ColunaTabela(dbName = "quantidade", setJavaName = "setQuantidade")
    private Integer quantidade;

    private Boolean isSaida;

    public EstoqueProduto() {
        quantidade = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setSaida(Boolean saida) {
        isSaida = saida;
    }

    public Long getIdProdutoFk() {
        return idProdutoFk;
    }

    public void setIdProdutoFk(Long idProdutoFk) {
        this.idProdutoFk = idProdutoFk;
    }

    public Integer getQuantidadeMov(Integer quantidadeInicial) {
        if (isSaida) {
            return quantidadeInicial -= quantidade;
        }
        return quantidadeInicial += quantidade;
    }

    public Long getIdProduto() {
        return this.produto.getId();
    }

    public void populateEstoqueProduto(Produto produto) {
        setQuantidade(produto.getQuantidadeInicial());
        setIdProdutoFk(produto.getId());
    }
}
