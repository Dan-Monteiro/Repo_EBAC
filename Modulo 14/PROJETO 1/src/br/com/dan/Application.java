package br.com.dan;

import br.com.dan.dao.ClienteMapDAO;
import br.com.dan.dao.IClienteDAO;
import br.com.dan.domain.Cliente;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Application {

    private static IClienteDAO clienteDAO;
    private static Map<String, String> opcoesValidas;
    private static Map<String, String> auxInformaçoes;
    private static final String CADASTRO = "1";
    private static final String CONSULTA = "2";
    private static final String EXCLUSAO = "3";
    private static final String ATUALIZACAO = "4";
    private static final String SAIR = "5";

    public static void main(String[] args) {
        clienteDAO = new ClienteMapDAO();

        opcoesValidas = new HashMap<>() {{
            put(CADASTRO, "Cadastro");
            put(CONSULTA, "Consulta");
            put(EXCLUSAO, "Exclusão");
            put(ATUALIZACAO, "Atualização");
            put(SAIR, "sair");
        }};
        menu();
    }

    public static void menu() {
        String opcao = obterOpcao();

        while (!isOpcaoValida(opcao)) {
            if("".equals(opcao) || opcao == null) {
                sair();
            }

            opcao = obterOpcao();
        }

        switch (opcao) {
            case CADASTRO:
                cadastrar();
                break;
            case CONSULTA:
                consultar();
                break;
            case EXCLUSAO:
                excluir();
                break;
            case ATUALIZACAO:
                atualizar();
                break;
            case SAIR:
                sair();
                break;
        }

        menu();
    }

    private static void atualizar() {
        String texto_entrada = obterEntrada("Informe o CPF do cliente", opcoesValidas.get(ATUALIZACAO));
        Long entrada = null;

        if (texto_entrada == null) {
            menu();
            return;
        }

        try {
            entrada = Long.parseLong(texto_entrada);
        } catch (Exception ex) {
            exibirMensagem("Valor de CPF inválido, informar um CPF válido!", "Erro", JOptionPane.ERROR_MESSAGE);
            atualizar();
        }

        Cliente cliente = clienteDAO.consultar(entrada);

        if (cliente != null) {
            String _entrada = obterEntrada("Informe os novos dados do cliente separado por vírgulas conforme exemplo: Nome,CPF,Tel,Logradouro,Numero,Cidade,Estado,", opcoesValidas.get(ATUALIZACAO));
            cliente = montarClient(_entrada);
            if(cliente == null) {
                exibirMensagem("Necessário informar um CPF!", opcoesValidas.get(ATUALIZACAO), JOptionPane.ERROR_MESSAGE);
                atualizar();
                return;
            }
            clienteDAO.atualizar(cliente);
            exibirMensagem("Cliente Atualizado!", opcoesValidas.get(ATUALIZACAO), JOptionPane.INFORMATION_MESSAGE);
        } else {
            exibirMensagem("Cliente não encontrado!", opcoesValidas.get(ATUALIZACAO), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void excluir() {
        String texto_entrada =obterEntrada("Informe o CPF do cliente", opcoesValidas.get(EXCLUSAO));
        Long entrada = null;

        if (texto_entrada == null) {
            menu();
            return;
        }

        try {
            entrada = Long.parseLong(texto_entrada);
        } catch (Exception ex) {
            exibirMensagem("Valor de CPF inválido, informar um CPF válido!", "Erro", JOptionPane.ERROR_MESSAGE);
            excluir();
        }

        Cliente cliente = clienteDAO.consultar(entrada);
        if (cliente != null) {
            clienteDAO.excluir(cliente.getCpf());
            exibirMensagem("Cliente Removido!", opcoesValidas.get(EXCLUSAO), JOptionPane.INFORMATION_MESSAGE);
        } else {
            exibirMensagem("Cliente não encontrado!", opcoesValidas.get(EXCLUSAO), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void consultar() {
        String texto_entrada = obterEntrada("Informe o CPF do cliente", opcoesValidas.get(CONSULTA));
        Long entrada = null;

        if (texto_entrada == null) {
            menu();
            return;
        }

        try {
            entrada = Long.parseLong(texto_entrada);
        } catch (Exception ex) {
            exibirMensagem("Valor de CPF inválido, informar um CPF válido!", "Erro", JOptionPane.ERROR_MESSAGE);
            consultar();
        }

        Cliente cliente = clienteDAO.consultar(entrada);
        if (cliente != null) {
            exibirMensagem("Cliente encontrado: " + cliente, opcoesValidas.get(CONSULTA), JOptionPane.INFORMATION_MESSAGE);
        } else {
            exibirMensagem("Cliente não encontrado!", opcoesValidas.get(CONSULTA), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void cadastrar() {
        String entrada = obterEntrada("Informe os dados do cliente separado por vírgulas conforme exemplo: Nome=Nome_Cliente,CPF=CPF_Cliente,Tel=Tel_Cliente,Logradouro=Logradouro_Cliente,Numero=Numero_Cliente,Cidade=Cidade_Cliente,Estado=Estado_Cliente", opcoesValidas.get(CADASTRO));

        if (entrada == null) {
            menu();
        }

        Cliente cliente = montarClient(entrada);;
        if (cliente == null) {
           exibirMensagem("Necessário informar ao menos o CPF!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
           cadastrar();
        }

        boolean isCadastrado = clienteDAO.cadastrar(cliente);

        if (isCadastrado) {
            exibirMensagem("Cliente cadastrado com sucesso!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        } else {
            exibirMensagem("Cliente já possui cadastro!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static Cliente montarClient(String entrada) {
        if ("".equals(entrada) || entrada == null) return null;

        auxInformaçoes = new HashMap<>();

        String[] grupoInformacoes = entrada.split(",");

        for (String grupo: grupoInformacoes) {
            String[] propriedades = grupo.split("=");
            if (propriedades.length < 2) continue;
            for (Field propriedadeCliente : Cliente.class.getDeclaredFields()) {
                String chave_prop = propriedades[0];
                String valor_prop = propriedades[1];
                if (propriedadeCliente.getName().equals(chave_prop.toLowerCase(Locale.ROOT))) {
                    auxInformaçoes.put(propriedadeCliente.getName(), valor_prop);
                }
            }
        }

        if (auxInformaçoes.get("cpf") == null) return null;

        Long cpf = null;
        Integer numero = null;

        if (auxInformaçoes.get("cpf") != null) {
            cpf = Long.parseLong(auxInformaçoes.get("cpf"));
        }

        if(auxInformaçoes.get("numero") != null) {
           numero = Integer.parseInt(auxInformaçoes.get("numero"));
        }

        return new Cliente(
            auxInformaçoes.get("nome"),
            cpf,
            auxInformaçoes.get("tel"),
            auxInformaçoes.get("logradouro"),
            numero,
            auxInformaçoes.get("cidade"),
            auxInformaçoes.get("estado")
        );
    }

    private static void sair() {
       exibirMensagem("Até Logo!", "Sair", JOptionPane.INFORMATION_MESSAGE);
       System.exit(0);
    }

    private static boolean isOpcaoValida(String opcao) {
        return opcoesValidas.containsKey(opcao);
    }

    private static String obterOpcao() {
        return JOptionPane.showInputDialog(
                null,
                "Digite 1 para cadastro, 2 para consultar, 3 para excluir, 4 para atualizar, 5 para sair",
                "Menu",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static String obterEntrada(String mensagem, String titulo) {
        return  JOptionPane.showInputDialog(
                null,
                mensagem,
                titulo,
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static void exibirMensagem(String mensagem, String titulo, int tipoMensagem) {
        JOptionPane.showMessageDialog(
                null,
                mensagem,
                titulo,
                tipoMensagem
        );
    }
}
