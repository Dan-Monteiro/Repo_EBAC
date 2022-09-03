package br.com.dan.service;

import br.com.dan.Contrato;
import br.com.dan.dao.IContratoDao;

public class ContratoService implements IContratoService {

    private IContratoDao contratoDao;

    public ContratoService(IContratoDao dao) {
        this.contratoDao = dao;
    }

    @Override
    public String salvar() {
        contratoDao.salvar();
        return "Sucesso";
    }

    @Override
    public Contrato buscar(Long codigo) {
        // Busca do banco e retorna o contrato
        Contrato contrato = contratoDao.buscar(codigo);
        return contrato;
    }

    @Override
    public boolean excluir(Long codigo) {
        // Executa lógica exclusão e retorna true/false para operação
        return contratoDao.excluir(codigo);
    }

    @Override
    public boolean atualizar(Long codigo) {
        // Executa lógica busca e atualização e retorna true/false para operação
        return contratoDao.atualizar(codigo);
    }
}
