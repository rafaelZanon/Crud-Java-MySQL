package br.com.cliente.aplicacao.cliente.model;

public class Admin {

    private String user;
    private int pass;

    //GETTERS
    public String getLogin() {
        return user;
    }

    public int getPass() {return pass;}

    //SETTERS
    public void setLogin(String user) {
        this.user = user;
    }

    public void setPass(int pass){this.pass = pass;}

    public boolean isPermissionAdmin(int pass) {
        if (pass == 1245){
            return true;
        }
        return false;
    }

    //Metodos obrigatorios
    @Override
    public String toString ()
    {
        String ret="";

        ret+="User: "+this.user+"\n";
        ret+="Senha: "+this.pass;

        return ret;
    }

}
