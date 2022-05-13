package br.com.cliente.aplicacao.cliente.model;


//OBSERVAÇÃO!!!
/*****ESSA CLASSE NÃO FOI CRIADA POR MIM********/
/********CLASSE CRIADA PELO MEU PROFESSOR ANDRE*******/
public class Programa {
    public static void main(String[] args) {
        try {
            Logradouro logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", "13033205");
            System.out.println(logradouro);
        } catch (Exception erro) {
            System.err.println(erro.getMessage());
        }
    }
}
