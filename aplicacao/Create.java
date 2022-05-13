package br.com.cliente.aplicacao.cliente.aplicacao;

import br.com.cliente.aplicacao.cliente.model.Cliente;
import br.com.cliente.aplicacao.cliente.dao.ClienteDAO;

public class Create {
    public static void main(String[] args) {

        ClienteDAO clienteDAO = new ClienteDAO();


        Cliente cliente = new Cliente();

        cliente.setNome("Tania Zanon");
        cliente.setIdade(49);
        cliente.setCep("13063020");
        cliente.setNumeroCasa(1088);
        cliente.setComplemento("");

        clienteDAO.save(cliente);




    }
}
