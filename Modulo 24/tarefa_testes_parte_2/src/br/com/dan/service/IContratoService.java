package br.com.dan.service;

import br.com.dan.Contrato;

public interface IContratoService {
    String salvar();
    Contrato buscar(Long codigo);
    boolean excluir(Long codigo);
    boolean atualizar(Long codigo);

}
