package br.com.cliente.aplicacao.cliente.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    //usuario do banco
    private static final String USERNAME = "root";

    //senha do banco
    private static final String PASSWORD = "";

    //caminho banco de dados porta, nome banco de dados (cliente)
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/cliente";

    //Conexão com o banco de dados

    public static Connection createConnecticonToMySQL() throws Exception {

        //faz com que a classe seja carregada pela JVM
        Class.forName("com.mysql.jdbc.Driver");

        //Cria conexão com o banco de dados
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        return connection;
    }

    public static void main(String[] args) throws Exception {
        //recuperar uma conexão com o banco de dados se tiver

        Connection con = createConnecticonToMySQL();

        //testar se a conexão é nula
        if (con != null) {
            System.out.println("Conexão estabelecida com sucesso");
            con.close();
        }
    }


}
