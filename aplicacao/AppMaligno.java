package br.com.cliente.aplicacao.cliente.aplicacao;

import br.com.cliente.aplicacao.cliente.dao.AdminDAO;
import br.com.cliente.aplicacao.cliente.dao.ClienteDAO;
import br.com.cliente.aplicacao.cliente.model.*;


public class AppMaligno {
    public static void main(String[] args) throws Exception {

        boolean cadastrado = false;
        boolean logado = false;
        String inputDate = "";
        int inputNumber = 0;

        ClienteDAO clienteDAO = new ClienteDAO();

        while (true) {
            System.out.println("|-------------------------------------------|");
            System.out.println("|                                           |");
            System.out.println("| Seja bem vindo ao sistema de Consulta CEP |");
            System.out.println("|                                           |");
            System.out.println("|-------------------------------------------|");
            System.out.println("\n");

            System.out.println("Efetue seu login [Digite 1] ou cadastre-se [Digite 2] para poder usar o sistema!\n");
            System.out.println("Digite a opcao desejada\n");

            try {
                inputNumber = Teclado.getUmInt();
            } catch (Exception e) {
                throw new Exception("A opcao precisa se um numero");
            }


            if (inputNumber == 2) {
                while (!cadastrado) {
                    System.out.println("Voce escolheu se cadastrar digite o pinCode para pode se tornar um Admin\n");
                    try {
                        inputNumber = Teclado.getUmInt();
                    } catch (Exception e) {
                        throw new Exception("A opcao precisa se um numero");
                    }
                    Admin singUpAdm = new Admin();
                    boolean x = singUpAdm.isPermissionAdmin(inputNumber);

                    if (x) {
                        System.out.println("Inicie seu Cadastro..\n");

                        System.out.println("Digite um User Name..");
                        inputDate = Teclado.getUmString();
                        singUpAdm.setLogin(inputDate);

                        System.out.println("Digite uma senha...");
                        try {
                            inputNumber = Teclado.getUmInt();
                        } catch (Exception e) {
                            throw new Exception("A opcao precisa se um numero");
                        }

                        singUpAdm.setPass(inputNumber);
                        cadastrado = true;
                        logado = true;
                        AdminDAO.singUpAdmin(singUpAdm);
                        break;
                    } else System.out.println("Voce nao tem permissao para realizar esse tipo de cadastro");
                }
            } else if (inputNumber == 1) {
                while (!logado) {
                    System.out.println("Voce escolheu logar..\n");
                    System.out.println("Coloque seu usuario..");
                    inputDate = Teclado.getUmString();
                    System.out.println("Coloque sua senha..");
                    try {
                        inputNumber = Teclado.getUmInt();
                    } catch (Exception e) {
                        throw new Exception("A opcao precisa se um numero");
                    }
                    boolean hasdate = AdminDAO.hasDate(inputDate, inputNumber);

                    if (hasdate) {
                        logado = true;
                        cadastrado = true;
                        break;
                    } else System.out.println("Usuario ou senha incorreto tente novamente...");

                }
            }


            String userName = inputDate;
            while (logado) {
                try {
                    System.out.println("Bem vindo " + userName + " Ao Sistema de Consulta CEP\n");

                    System.out.println("Oque deseja?\n");
                    System.out.println("Digite[1] : Cadastrar um novo Cliente");
                    System.out.println("Digite[2] : Buscar o CEP de um Cliente");
                    System.out.println("Digite[3] : Deletar um Cliente");
                    System.out.println("Digite[4] : Atualizar um dados de Clientes");
                    System.out.println("Digite[5] : Mudar login ou senha");
                    System.out.println("Digite[6] : Excluir conta");
                    System.out.println("Digite[7] : Sair");
                    inputNumber = Teclado.getUmInt();

                    if (inputNumber == 1) {

                        System.out.println("Voce escolheu a opcao de cadastrar um novo Cliente...");
                        System.out.println("Digite o nome do Cliente: ");
                        Cliente cliente = new Cliente();
                        inputDate = Teclado.getUmString();
                        cliente.setNome(inputDate);
                        System.out.println("Digite a Idade do Cliente: ");
                        inputNumber = Teclado.getUmInt();
                        cliente.setIdade(inputNumber);
                        System.out.println("Digite o CEP: ");
                        inputDate = Teclado.getUmString();
                        cliente.setCep(inputDate);
                        System.out.println("Digite o numero da Casa: ");
                        inputNumber = Teclado.getUmInt();
                        cliente.setNumeroCasa(inputNumber);
                        System.out.println("Digite o Complemento (se tiver): ");
                        inputDate = Teclado.getUmString();
                        cliente.setComplemento(inputDate);

                        try {
                            clienteDAO.save(cliente);
                            System.out.println("Cliente " + cliente.getNome() + " cadastrado com sucesso\n");
                        } catch (Exception a) {
                            throw new Exception("Erro ao cadastrar Cliente!");
                        }

                    } else if (inputNumber == 2) {
                        System.out.println("Voce escolheu buscar um CEP");
                        System.out.println("Opcoes: Buscar CEP de um Cliente [1]");
                        System.out.println("        Buscar CEP digitando [2]");
                        System.out.println("Selecione a opcao..");
                        inputNumber = Teclado.getUmInt();
                        if (inputNumber == 1) {
                            String localCEP;
                            System.out.println("Digite o nome do Cliente..");
                            inputDate = Teclado.getUmString();
                            for (Cliente cliente : clienteDAO.getCliente(inputDate)) {
                                Logradouro logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", cliente.getCep());
                                System.out.println("Segue a lista com as informacoes da rua: \n");
                                System.out.println(logradouro);
                            }
                        } else if (inputNumber == 2) {
                            System.out.println("Digite um CEP");
                            inputDate = Teclado.getUmString();
                            Logradouro logradouro = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", inputDate);
                            System.out.println("Dados encontrados..\n");
                            System.out.println(logradouro);

                        } else System.out.println("Houve um erro desconhecido..");


                    } else if (inputNumber == 3) {
                        System.out.println("Voce escolheu a opcao de excluir um Cliente...");
                        System.out.println("Digite o id do Cliente que deseja excluir: ");
                        inputNumber = Teclado.getUmInt();
                        clienteDAO.deleteByID(inputNumber);

                    } else if (inputNumber == 4) {
                        String name, newName, CEP, complemento;
                        int idade, numero = 0;
                        System.out.println("Voce escolheu a opcao de atualizacao do Cliente...");
                        System.out.println("Digite o nome do Cliente que voce quer atualizar, e deixe um dado que voce nao quer atualizar em branco...");
                        inputDate = Teclado.getUmString();
                        name = inputDate;
                        System.out.println("Digite o novo nome do Cliente...");
                        inputDate = Teclado.getUmString();
                        newName = inputDate;
                        System.out.println("Digite para atualizar a idade");
                        inputNumber = Teclado.getUmInt();
                        idade = inputNumber;
                        System.out.println("Digite para atualizar o CEP\n");
                        inputDate = Teclado.getUmString();
                        CEP = inputDate;
                        System.out.println("Digite o numero da Casa: ");
                        inputNumber = Teclado.getUmInt();
                        numero = inputNumber;
                        System.out.println("Digite o Complemento (se tiver): ");
                        inputDate = Teclado.getUmString();
                        complemento = inputDate;

                        Cliente clientezinho = new Cliente();
                        clientezinho.setNome(newName);
                        clientezinho.setIdade(idade);
                        clientezinho.setCep(CEP);
                        clientezinho.setNumeroCasa(numero);
                        clientezinho.setComplemento(complemento);
                        clienteDAO.update(clientezinho, name);
                        System.out.println("Dados de " + name + " atualizados");


                    } else if (inputNumber == 5) {
                        String newLogin;
                        int newSenha = 0;
                        System.out.println("Voce escolheu a opcao de alterar seu login e senha");
                        System.out.println("Digite o novo login..");
                        inputDate = Teclado.getUmString();
                        newLogin = inputDate;
                        System.out.println("Digite sua nova senha...");
                        inputNumber = Teclado.getUmInt();
                        newSenha = inputNumber;
                        AdminDAO.updateDate(newLogin, userName, newSenha);
                        System.out.println("Dados atualizados com sucesso..");
                        userName = newLogin;
                    } else if (inputNumber == 6) {
                        System.out.println("Voce escolheu a opcao de excluir conta..");
                        System.out.println("Deseja mesmo excluir sua conta? digite [1] para confirmar e [2] caso nao queira..");
                        inputNumber = Teclado.getUmInt();
                        if (inputNumber == 1) {
                            System.out.println("Digite seu login..");
                            inputDate = Teclado.getUmString();
                            System.out.println("Sua senha...");
                            inputNumber = Teclado.getUmInt();
                            AdminDAO.delAcount(inputDate, inputNumber);
                            System.out.println("Conta excluida com sucesso!");
                        }

                    } else if (inputNumber == 7) {
                        System.out.println("Logout realizado com sucesso\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        logado = false;
                        cadastrado = false;
                        break;
                    }
                } catch (Exception erro) {
                    erro.printStackTrace();
                    System.out.println(erro.getMessage());
                }
            }
        }
    }
}