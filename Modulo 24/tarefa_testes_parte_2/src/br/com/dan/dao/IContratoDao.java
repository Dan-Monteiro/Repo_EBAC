package br.com.dan.dao;

import br.com.dan.Contrato;

public interface IContratoDao {
    void salvar();
    Contrato buscar(Long id);
    boolean excluir(Long id);
    boolean atualizar(Long codigo);
}
