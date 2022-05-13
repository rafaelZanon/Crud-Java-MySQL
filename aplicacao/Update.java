package br.com.cliente.aplicacao.cliente.aplicacao;

import br.com.cliente.aplicacao.cliente.dao.ClienteDAO;
import br.com.cliente.aplicacao.cliente.model.Cliente;

public class Update {

    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = new Cliente();
        Cliente c1 = new Cliente();

        c1.setNome("Geraldo Zanon");
        c1.setIdade(50);
        c1.setCep("13063020");
        c1.setNumeroCasa(1088);
        c1.setComplemento("Quinta do marquês");
        c1.setId(4);

        clienteDAO.update(c1);

        System.out.println("DADOS ALTERADOS COM SUCESSO");


    }
}
