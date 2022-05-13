package br.com.cliente.aplicacao.cliente.aplicacao;

import br.com.cliente.aplicacao.cliente.dao.ClienteDAO;
import br.com.cliente.aplicacao.cliente.model.Cliente;
import br.com.cliente.aplicacao.cliente.model.ClienteWS;
import br.com.cliente.aplicacao.cliente.model.Logradouro;

public class Read{
    public static void main(String[] args) {

        ClienteDAO clienteDAO = new ClienteDAO();

      for (Cliente s: clienteDAO.getCliente()){
          System.out.println("Nome Cliente: " + s.getNome());
          System.out.println("Idade Cliente: " + s.getIdade());
          System.out.println("Numero da Casa: " + s.getNumeroCasa());

          String s1 = s.getCep();
          Logradouro logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", s1);
          System.out.println(logradouro);
      }

    }
}

