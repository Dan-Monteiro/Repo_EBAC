package br.com.dan.domain.dao;

import br.com.dan.domain.model.Produto;

public interface IProdutoDao {
    Produto cadastrar(Produto produto);
}