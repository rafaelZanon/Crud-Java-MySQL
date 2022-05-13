package br.com.cliente.aplicacao.cliente.dao;

import br.com.cliente.aplicacao.cliente.model.Cliente;
import br.com.cliente.aplicacao.cliente.factory.ConnectionFactory;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // C create - ok
    // R read - ok
    // U update - ok
    // D delete - ok

    //metodo create
    public void save(Cliente cliente) {

        String sql = "INSERT INTO cliente(nome, idade, cep, numerocasa, complemento) VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //criar uma conexão com o banco de dados
            conn = ConnectionFactory.createConnecticonToMySQL();

            //criamos uma PreparedStatement para executar uma QUERY
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //adicionando os valores que sao esperados pela query

            pstm.setString(1, cliente.getNome());
            pstm.setInt(2, cliente.getIdade());
            pstm.setString(3, cliente.getCep());
            pstm.setInt(4, cliente.getNumeroCasa());
            pstm.setString(5, cliente.getComplemento());

            //Executar a query

            pstm.execute();
            System.out.println("DADOS SALVOS COM SUCESSO");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            //fechar as conexões
            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //metodo read
    public List<Cliente> getCliente() {

        String sql = "SELECT * FROM cliente";

        List<Cliente> clientes = new ArrayList<Cliente>();

        Connection conn = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnecticonToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                Cliente cliente = new Cliente();

                //Recuperar o id
                cliente.setId(rset.getInt("id"));

                //Recuperar o nome
                cliente.setNome(rset.getString("nome"));

                //Recuperar a idade
                cliente.setIdade(rset.getInt("idade"));

                //Recuperar o cep
                cliente.setCep(rset.getString("cep"));

                //recuperar numeroCasa
                cliente.setNumeroCasa(rset.getInt("numeroCasa"));

                //recuperar complemento
                cliente.setComplemento(rset.getString("complemento"));

                clientes.add(cliente);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientes;
    }

    //metodo update
    public void update(Cliente cliente) {

        String sql = "UPDATE cliente SET nome = ?, idade = ?, cep = ?, numerocasa = ?, complemento = ? " +
                "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Criar conexão com o banco
            conn = ConnectionFactory.createConnecticonToMySQL();

            //Criar classe para executar a query

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //Adicionar os valores para atualizar

            pstm.setString(1, cliente.getNome());
            pstm.setInt(2, cliente.getIdade());
            pstm.setString(3, cliente.getCep());
            pstm.setInt(4, cliente.getNumeroCasa());
            pstm.setString(5, cliente.getComplemento());

            //Qual o ID do registro que deseja atualizar?
            pstm.setInt(6, cliente.getId());


            //executar a query
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //metodo delete
    public void deleteByID(int id) {

        String sql = "DELETE FROM cliente WHERE id = ?";

        Connection conn = null;

        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnecticonToMySQL();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    pstm.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
