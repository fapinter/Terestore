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
            System.out.print("LOGIN: ");
            String email = sc.nextLine();
            System.out.print("SENHA: ");
            String senha = sc.nextLine();
            int menu = peDAO.login(email,senha);
            switch (menu){
                case 1:
                    System.out.println("Bem vindo Admin");
                    MenuPrincipal.menuAdmin();
                    login = true;
                case 2:
                    System.out.println("Bem vindo Vendedor");
                    MenuPrincipal.menuSalesman();
                    login = true;
                case 0:
                    System.out.println("Login ou senha incorretos");
            }
        }
    }
}

class MenuPrincipal {

    private static Scanner scanner = new Scanner(System.in);

    public static void menuSalesman(){}
    public static void menuAdmin() {
        int opcaoMenuPrincipal;

        do {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Menu Pessoa");
            System.out.println("2. Menu Produto");
            System.out.println("3. Menu Venda");
            System.out.println("4. Menu Fornecedor");
            System.out.println("0. Sair");
            System.out.print("Digite sua opção: ");

            opcaoMenuPrincipal = scanner.nextInt();

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


    }

    private static void menuPessoa() {
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

