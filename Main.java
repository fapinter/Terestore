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

        Scanner sc = new Scanner(System.in);
        boolean login = false;
        while (!login){
            //Login do usuario ao sistema
            System.out.print("LOGIN: ");
            String email = sc.nextLine();
            System.out.print("SENHA: ");
            String senha = sc.nextLine();
            int menu = peDAO.login(email,senha);

            //Qual menu será acessado, Admin ou Vendedor
            switch (menu){
                case 1:
                    System.out.println("*** MENU ADMIN ***");
                    int option = menu();
                    switch (option){
                        case 1:

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
                    option = menu();
                    login = true;
                case 0:
                    System.out.println("Login ou senha incorretos");
            }
        }
    }

    public static int menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Pessoa: ");
        System.out.println("2. Produto: ");
        System.out.println("3. Vendas: ");
        System.out.println("4. Fornecedor: ");
        System.out.println("5. Fechamento do dia: ");
        System.out.println("0. Sair");
        System.out.print("Digite sua opção: ");
        int optionMenu = sc.nextInt();
        return optionMenu;
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

