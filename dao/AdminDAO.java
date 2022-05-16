package br.com.cliente.aplicacao.cliente.dao;

import br.com.cliente.aplicacao.cliente.factory.ConnectionFactory;
import br.com.cliente.aplicacao.cliente.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    static Connection conn;
    static PreparedStatement pstm;

    public static void singUpAdmin(Admin admin) throws Exception {
        String prepareSql = "insert into adminuser (login,password) values(?,?)";

        conn = ConnectionFactory.createConnecticonToMySQL();
        pstm = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(prepareSql);


        try {
            pstm = conn.prepareStatement(prepareSql);
            pstm.setString(1, admin.getLogin());
            pstm.setInt(2,admin.getPass());

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateDate(String login,String newLogin,int pass)throws Exception{
        String prepareSql = "UPDATE adminuser SET login = ?, password = ? WHERE login = ?";

        conn = ConnectionFactory.createConnecticonToMySQL();
        pstm = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(prepareSql);


        try {
            pstm = conn.prepareStatement(prepareSql);
            pstm.setString(1, login);
            pstm.setInt(2,pass);
            pstm.setString(3,newLogin);

            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void delAcount(String login, int pass) throws Exception {
        String prepare = "DELETE FROM adminuser WHERE login = ? and password = ?";

        conn = ConnectionFactory.createConnecticonToMySQL();
        pstm = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(prepare);

        if (login == null) throw new SQLException("ERRO; INSIRA UM NOME PARA PODER EXCLUIR O DADO");

        try {
            if (!hasDate(login,pass)) {
                pstm = conn.prepareStatement(prepare);
                pstm.setString(1, login);
                pstm.setInt(2, pass);
                pstm.execute();
            }
            else throw new SQLException("Erro ao excluir o usuario.");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static boolean hasDate(String user,int pass) throws Exception {
        // Vou usar essa variavel para saber se ele sai daqui com alguma coisa encontrada no banco de dados
        boolean rt = false;
        List<Admin> adminList = new ArrayList<Admin>();
        try {
            // Gero a conex�o e depois eu removo a mesma
            conn = ConnectionFactory.createConnecticonToMySQL();
            String prepareSql = "SELECT * FROM adminuser WHERE login = " + "'"+user+"'"+" and password ="+ pass;
            pstm = (PreparedStatement) conn.prepareStatement(prepareSql);
            ResultSet rs = (ResultSet) pstm.executeQuery();

            while (rs.next()) {
                Admin adminLogin = new Admin();
                Admin adminSenha = new Admin();

                adminLogin.setLogin(rs.getString("login"));
                adminSenha.setPass(rs.getInt("password"));

                adminList.add(adminLogin);
                adminList.add(adminSenha);
            }

            if(adminList.size()>=2){
                rt = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }//finally {
        // if (conn != null)conn.close();
        // if (pstm != null)pstm.close();
        //}
        return rt;
    }


    //Nessa classe gero uma lista de CPF para poder extrair a string dele j� que cada aluno sempre tera s� um registro

    public static List<Admin> getAdmin(String user, int pass) throws Exception {
        List<Admin> adminList = new ArrayList<Admin>();
        // Vou usar essa variavel para saber se ele sai daqui com alguma coisa encontrada no banco de dados
        try {
            // Gero a conex�o e depois eu removo a mesma
            conn = ConnectionFactory.createConnecticonToMySQL();
            String prepareSql = "SELECT * FROM adminuser WHERE login = " + "'"+user+"'"+" and password ="+ pass;
            pstm = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(prepareSql);
            ResultSet rs = (ResultSet) pstm.executeQuery();

            while (rs.next()) {
                Admin admin1 = new Admin();

                admin1.setLogin(rs.getString("login"));
                admin1.setPass(rs.getInt("password"));


                adminList.add(admin1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (conn != null)conn.close();
        if (pstm != null)pstm.close();

        return adminList;
    }


}
