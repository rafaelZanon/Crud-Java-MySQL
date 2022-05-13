package br.com.cliente.aplicacao.cliente.aplicacao;

import br.com.cliente.aplicacao.cliente.dao.ClienteDAO;

public class Delete {
    public static void main(String[] args) {

        ClienteDAO clienteDAO = new ClienteDAO();

        // Passe o ID nos parenteses para deletar.

        clienteDAO.deleteByID(4);

        System.out.println("DADOS DELETADOS COM SUCESSO");

    }
}
