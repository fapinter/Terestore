import dao.*;
import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        PersonDAO pDAO = new PersonDAO();
        SupplierDAO sDAO = new SupplierDAO();

        // Exemplo de inserção de um fornecedor
        // Supplier sp = new Supplier("03254085000122", "ProdutoraMT", "Tarso Bertolini",
        // "tarsinho@outlook.com", "123456", "41992730411", "Curitiba",
        // "Paraná", "Brasil", "Rua Manoel Eufrásio", 480);

        // Scanner sc = new Scanner(System.in);
        // System.out.println("Digite o email para remover");
        // String email = sc.nextLine();

        ResultSet rs = sDAO.listSupplier();
        try {
            int i = 1;
            while (rs.next()) {
                System.out.println("*** Supplier " + i + " ***");
                System.out.println("Number_address : " + rs.getString("number_address"));
                i += 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("rodou");

        MenuPrincipal.menu();
    }
}

class MenuPrincipal {

    private static Scanner scanner = new Scanner(System.in);

    public static void menu() {
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
                    menuPessoa();
                    break;
                case 2:
                    menuProduto();
                    break;
                case 3:
                    menuVenda();
                    break;
                case 4:
                    menuFornecedor();
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcaoMenuPrincipal != 0);

        scanner.close();
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
}

