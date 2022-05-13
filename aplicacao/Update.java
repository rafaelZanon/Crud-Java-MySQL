package br.com.cliente.aplicacao.cliente.aplicacao;

import br.com.cliente.aplicacao.cliente.dao.ClienteDAO;
import br.com.cliente.aplicacao.cliente.model.Cliente;

public class Update {

    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = new Cliente();
        Cliente c1 = new Cliente();

        //digite seu nome para o update
        c1.setNome("");
        //digite sua idade
        c1.setIdade(00);
        //digite seu cep
        c1.setCep("00000000");
        //digite o numero da sua casa
        c1.setNumeroCasa(0000);
        //complemento
        c1.setComplemento("");
        //digite o id que voce quer dar update
        c1.setId(4);

        clienteDAO.update(c1);

        System.out.println("DADOS ALTERADOS COM SUCESSO");


    }
}
