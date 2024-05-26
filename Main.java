import dao.*;
import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        PersonDAO peDAO = new PersonDAO();
        SupplierDAO suDAO = new SupplierDAO();
        ProductsDAO poDAO = new ProductsDAO();
        SalesDAO saDAO = new SalesDAO();

        ResultSet rs;
        Scanner sc = new Scanner(System.in);
        boolean login = false;
        while (!login){
            //Login do usuario ao sistema
            System.out.print("LOGIN: ");
            String email = sc.nextLine();
            System.out.print("SENHA: ");
            String senha = sc.nextLine();
            int menu = peDAO.login(email,senha);
            int option;
            //Qual menu será acessado, Admin ou Vendedor
            switch (menu){
                case 1:
                    System.out.println("*** MENU ADMIN ***");
                    option = FirstMenu();
                    switch (option){
                        case 1:
                            int optionMenu = personAdmin();
                            switch(optionMenu){
                                //Cadastrar Admin
                                case 1:
                                    peDAO.insertAdmin(insertAdmin());
                                //Cadastrar Vendedor
                                case 2:
                                    peDAO.insertPerson(insertClientSalesman(2));
                                //Cadastrar Cliente
                                case 3:
                                    peDAO.insertPerson(insertClientSalesman(3));
                                //Listar Vendedores
                                case 4:
                                    rs = peDAO.listPerson(2);
                                    try{
                                        int i = 1;
                                        while(rs.next()){
                                            System.out.println("\n*** Vendedor "+i+ " ***");
                                            System.out.println("CPF: "+ rs.getString("cpf"));
                                            System.out.println("Nome: "+ rs.getString("first_name")+ " " + rs.getString("last_name"));
                                            System.out.println("Email: " + rs.getString("email"));
                                            System.out.println("Data de nascimento: "+ rs.getString("birthdate"));
                                            System.out.println("Celular : "+ rs.getString("cellphone"));
                                            System.out.println("Endereço: "+rs.getString("address")+ " "+ rs.getInt("number_address"));
                                            System.out.println(rs.getString("city")+" "+ rs.getString("state")+ " "+ rs.getString("country"));
                                            i += 1;
                                        }
                                    }
                                    catch(SQLException ex){ex.printStackTrace();}
                                //Listar Clientes
                                case 5:
                                    rs = peDAO.listPerson(3);
                                    try{
                                        int i = 1;
                                        while(rs.next()){
                                            System.out.println("\n*** Cliente "+i+ " ***");
                                            System.out.println("CPF: "+ rs.getString("cpf"));
                                            System.out.println("Nome: "+ rs.getString("first_name")+ " " + rs.getString("last_name"));
                                            System.out.println("Email: " + rs.getString("email"));
                                            System.out.println("Data de nascimento: "+ rs.getString("birthdate"));
                                            System.out.println("Celular : "+ rs.getString("cellphone"));
                                            System.out.println("Endereço: "+rs.getString("address")+ " "+ rs.getInt("number_address"));
                                            System.out.println(rs.getString("city")+" "+ rs.getString("state")+ " "+ rs.getString("country"));
                                            i += 1;
                                        }
                                    }
                                    catch(SQLException ex){ex.printStackTrace();}
                                //Editar Cliente
                                case 6:

                                //Remover Vendedor
                                case 7:


                                //Remover Cliente
                                case 8:

                                default:
                                    System.out.println("Saindo ...");
                            }

                        case 5:
                            System.out.println("Digite a data que deseja do fechamento");
                            System.out.print("no modelo YYYY-MM-DD: ");
                            String date = sc.nextLine();
                            ResultSet rs1 = saDAO.totalOfDay(date);
                            double totalMoney = 0.00;
                            try{
                                while(rs1.next()){
                                    totalMoney += rs1.getDouble("total_price");
                                }
                            }
                            catch(SQLException ex){ex.printStackTrace();}
                            System.out.println("Lucro total do dia "+date+ ": "+ totalMoney);

                    }
                    login = true;
                case 2:
                    System.out.println("*** MENU VENDEDORES ***");
                    option = FirstMenu();
                    switch(option){
                        case 1:
                            continue;
                    }
                    login = true;
                case 0:
                    System.out.println("Login ou senha incorretos");
            }
        }
    }

    public static Person insertAdmin(){
        Scanner sc = new Scanner(System.in);
        String cpf;
        String first_name;
        String email;
        String password;

        //Fazer verificação das entradas
        System.out.print("Digite o CPF: ");
        cpf = sc.nextLine();
        System.out.print("Digitie o primeiro nome: ");
        first_name = sc.nextLine();
        System.out.print("Digite o email: ");
        email = sc.nextLine();
        System.out.print("Digite a senha: ");
        password = sc.nextLine();
        return new Person(cpf,first_name, email, password, 1);
    }
    public static Person insertClientSalesman(int type_person){
        Scanner sc = new Scanner(System.in);
        String cpf;
        String first_name;
        String email;
        String password;
        String last_name;
        String birthdate;
        String cellphone;
        String city;
        String state;
        String country;
        String address;
        int number_address;

        //Fazer verificação das entradas
        System.out.println("Digite o cpf: ");
        cpf = sc.nextLine();
        System.out.println("Digite o primeiro nome: ");
        first_name = sc.nextLine();
        System.out.println("Digite o email: ");
        email = sc.nextLine();
        System.out.println("Digite a senha: ");
        password = sc.nextLine();
        System.out.println("Digite o sobrenome: ");
        last_name = sc.nextLine();
        System.out.println("Digite a data de nascimento: ");
        birthdate = sc.nextLine();
        System.out.println("Digite o número de celular: ");
        cellphone = sc.nextLine();
        System.out.println("Digite a cidade: ");
        city = sc.nextLine();
        System.out.println("Digite o estado: ");
        state = sc.nextLine();
        System.out.println("Digite o país: ");
        country = sc.nextLine();
        System.out.println("Digite o endereço(sem o número): ");
        address = sc.nextLine();
        System.out.print("Digite o numero do endereço");
        number_address = sc.nextInt();

        return new Person(cpf,first_name,email,password, type_person,last_name,birthdate,cellphone,city,state,country,address,number_address);
    }
    public static void editClient(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o CPF do cliente para editar: ");
        String cpf = sc.nextLine();
        System.out.println("*** QUAL DADO DESEJA EDITAR");
        System.out.println("1. CPF: ");
        System.out.println("2. Primeiro nome");
        System.out.println("3. Email");
        System.out.println("4. Senha: ");
        System.out.println("5. Sobrenome");
        System.out.println("6. Data de nascimento");
        System.out.println("7. Número de celular");
        System.out.println("8. Cidade");
        System.out.println("9. Estado");
        System.out.println("10. País");
        System.out.println("11. Endereço");
        System.out.println("12. Número do endereço");
        int dado = sc.nextInt();

    }
    public static int FirstMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Pessoas: ");
        System.out.println("2. Produto: ");
        System.out.println("3. Vendas: ");
        System.out.println("4. Fornecedor: ");
        System.out.println("5. Fechamento do dia: ");
        System.out.println("0. Sair");
        System.out.print("Digite sua opção: ");
        int optionMenu = sc.nextInt();
        return optionMenu;
    }
    public static int personAdmin(){
        int option;
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Cadastrar Admin: ");
        System.out.println("2. Cadastrar Vendedor: ");
        System.out.println("3. Cadastrar Cliente: ");
        System.out.println("4. Listar Vendedores: ");
        System.out.println("5. Listar Clientes: ");
        System.out.println("6. Editar CLiente: ");
        System.out.println("7. Remover Vendedor: ");
        System.out.println("8. Remover CLiente: ");
        System.out.println("9. Sair: ");
        System.out.print("Digite sua opção: ");
        option = sc.nextInt();
        return option;
    }
    public static void removePerson(String person){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o email do "+person+ ": ");

    }

    /*public static void menuAdmin() {
        int opcaoMenuPrincipal;

        do {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Menu Pessoa");
            System.out.println("2. Menu Produto");
            System.out.println("3. Menu Venda");
            System.out.println("4. Menu Fornecedor");
            System.out.println("0. Sair");
            System.out.print("Digite sua opção: ");

            opcaoMenuPrincipal = sc.nextInt();

            switch (opcaoMenuPrincipal) {
                case 1:
                    int opcaoMenuSecundario = secondaryMenu("Pessoa");
                    switch(opcaoMenuSecundario){
                        case 1:
                            System.out.println("Inserir");
                        case 2:
                            System.out.println("Remove");
                        case 3:
                            System.out.println("List");
                        case 4:
                            System.out.println("Edit");
                        case 0:
                            System.out.println("Go back");
                        default:
                            System.out.println("Not found, return");
                    }
                case 2:
                    secondaryMenu("Produto");
                    break;
                case 3:
                    menuVenda();
                    break;
                case 4:
                    secondaryMenu("Fornecedor");
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcaoMenuPrincipal != 0);


    }*/

    private static void menuPessoa() {
        Scanner scanner = new Scanner(System.in);
        int opcaoMenuPessoa;

        do {
            System.out.println("\nMenu Pessoa:");
            System.out.println("1. Inserir Pessoa");
            System.out.println("2. Atualizar Pessoa");
            System.out.println("3. Deletar Pessoa");
            System.out.println("4. Consultar Pessoa");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Digite sua opção: ");

            opcaoMenuPessoa = scanner.nextInt();

            switch (opcaoMenuPessoa) {
                case 1:
                    System.out.println("insert");
                    break;
                case 2:
                    System.out.println("delet");
                    break;
                case 3:
                    System.out.println("update");
                    break;
                case 4:
                    System.out.println("select");
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcaoMenuPessoa != 0);
    }

    private static void menuProduto() {
        Scanner scanner = new Scanner(System.in);
        int opcaoMenuProduto;

        do {
            System.out.println("\nMenu Produto:");
            System.out.println("1. Inserir Produto");
            System.out.println("2. Atualizar Produto");
            System.out.println("3. Deletar Produto");
            System.out.println("4. Consultar Produto");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Digite sua opção: ");

            opcaoMenuProduto = scanner.nextInt();

            switch (opcaoMenuProduto) {
                case 1:
                    System.out.println("insert");
                    break;
                case 2:
                    System.out.println("update");
                    break;
                case 3:
                    System.out.println("delet");
                    break;
                case 4:
                    System.out.println("select");
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcaoMenuProduto != 0);
    }

    private static void menuVenda() {
        Scanner scanner = new Scanner(System.in);
        int opcaoMenuVenda;

        do {
            System.out.println("\nMenu Venda:");
            System.out.println("1. Registrar Venda");
            System.out.println("2. Consultar Venda");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Digite sua opção: ");

            opcaoMenuVenda = scanner.nextInt();

            switch (opcaoMenuVenda) {
                case 1:
                    System.out.println("registrar venda");
                    break;
                case 2:
                    System.out.println("consultar venda");
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcaoMenuVenda != 0);
    }

    private static void menuFornecedor() {
        Scanner scanner = new Scanner(System.in);
        int opcaoMenuFornecedor;

        do {
            System.out.println("\nMenu Fornecedor:");
            System.out.println("1. Inserir Fornecedor");
            System.out.println("2. Atualizar Fornecedor");
            System.out.println("3. Deletar Fornecedor");
            System.out.println("4. Consultar Fornecedor");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Digite sua opção: ");

            opcaoMenuFornecedor = scanner.nextInt();

            switch (opcaoMenuFornecedor) {
                case 1:
                    System.out.println("insert");
                    break;
                case 2:
                    System.out.println("update");
                    break;
                case 3:
                    System.out.println("delet");
                    break;
                case 4:
                    System.out.println("select");
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcaoMenuFornecedor != 0);
    }
    public static int secondaryMenu(String type_menu){
        Scanner sc = new Scanner(System.in);
        int optionMenu;
        System.out.println("Menu "+type_menu);
        System.out.println("1. Inserir "+type_menu+": ");
        System.out.println("2. Atualizar "+type_menu+": ");
        System.out.println("3. Deletar "+type_menu+": ");
        System.out.println("4. Listar "+type_menu+": ");
        System.out.println("0. Voltar ao Menu Principal: ");
        System.out.print("Digite a opção desejada: ");
        optionMenu = sc.nextInt();
        return optionMenu;
    }
}

