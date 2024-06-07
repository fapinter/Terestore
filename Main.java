import dao.*;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PersonDAO peDAO = new PersonDAO();
        SupplierDAO suDAO = new SupplierDAO();
        ProductsDAO poDAO = new ProductsDAO();
        SalesDAO saDAO = new SalesDAO();
        Menu menu = new Menu();


        ResultSet rs;
        Scanner sc = new Scanner(System.in);
        Scanner ScannerDate = new Scanner(System.in);
        
        //Pega o valor atual do id que será utilizado como id
        //na tabela sales
        int id_sale = saDAO.getIDsale();
        boolean login = false;
        while (!login){
            //Login do usuario ao sistema
            System.out.print("LOGIN: ");
            String email = Validation.validateEmail(sc);
            System.out.print("SENHA: ");
            String senha = sc.nextLine();
            int type_menu = peDAO.login(email,senha);
            int option = 1;
            int optionMenu;

            //Qual menu será acessado, Admin ou Vendedor
            switch (type_menu){
                case 1:
                    while(option != 0) {
                        System.out.println("\n*** MENU ADMIN ***");
                        option = menu.FirstMenu();
                        switch (option) {
                            //Pessoas
                            case 1:
                                optionMenu = menu.personAdmin();
                                switch (optionMenu){
                                    //Cadastrar Admin
                                    case 1:
                                        peDAO.insertAdmin(menu.insertAdmin());
                                        break;
                                    //Cadastrar Vendedor
                                    case 2:
                                        peDAO.insertPerson(menu.insertClientSalesman(2));
                                        break;

                                    //Cadastrar Cliente
                                    case 3:
                                        peDAO.insertPerson(menu.insertClientSalesman(3));
                                        break;
                                    //Listar Vendedores
                                    case 4:
                                        rs = peDAO.listPerson(2);
                                        menu.listPerson(rs, "Vendedor");
                                        break;
                                    //Listar Clientes
                                    case 5:
                                        rs = peDAO.listPerson(3);
                                        menu.listPerson(rs, "Cliente");
                                        break;
                                    //Editar Cliente
                                    case 6:
                                        menu.editClient(peDAO);
                                        break;
                                    //Remover Vendedor
                                    case 7:
                                        peDAO.removeSalesman(menu.removePerson("vendedor"), 2);
                                        break;

                                    //Remover Cliente
                                    case 8:
                                        peDAO.removeClient(menu.removePerson("cliente"), 3);
                                        break;

                                    case 0:
                                        System.out.println("voltando ao menu... ");
                                        break;
                                    default:
                                        System.out.println("Opção inválida... ");
                                        break;
                                }
                                break;

                            //Produtos
                            case 2:
                                optionMenu = menu.productsAdmin();
                                switch (optionMenu){
                                    //Cadastrar produto
                                    case 1:
                                        poDAO.insertProduct(menu.insertProduct());
                                        break;

                                    //Listar produtos
                                    case 2:
                                        rs = poDAO.listProducts();
                                        menu.listProducts(rs);
                                        break;

                                    //Editar produtos
                                    case 3:
                                        menu.editProduct(poDAO);
                                        break;

                                    //Remover produto
                                    case 4:
                                        menu.removeProduct(poDAO);
                                        break;

                                    //Sair
                                    case 0:
                                        System.out.println("Voltando ao menu...");
                                        break;

                                    default:
                                        System.out.println("Opção inválida...");
                                        break;
                                }
                                break;
                            //Vendas
                            case 3:
                                optionMenu = menu.salesAdmin();
                                if(optionMenu == 1){
                                    rs = saDAO.listSales();
                                    menu.listSales(rs, id_sale);
                                }
                                else if(optionMenu == 0){break;}
                                else{System.out.println("Opção inválida...");}
                                break;

                            //Fornecedor
                            case 4:
                                optionMenu = menu.supplierAdmin();
                                switch (optionMenu) {
                                    // insert supplier
                                    case 1:
                                        suDAO.insertSupplier(menu.insertSupplier());
                                        break;
                                    //listar supplier
                                    case 2:
                                        rs = suDAO.listSupplier();
                                        menu.listSupplier(rs);
                                        break;
                                    //editar supplier
                                    case 3:
                                        menu.editSupplier(suDAO);
                                        break;
                                    //remove supplier
                                    case 4:
                                        menu.removeSupplier(suDAO);
                                        break;
                                    
                                    case 0:
                                        System.out.println("voltando ao menu... ");
                                        break;
                                    default:
                                        System.out.println("Opção inválida...");    
                                }
                                break;
                            //Fechamento do dia
                            case 5:
                                menu.fechamentoDoDia(ScannerDate, saDAO);
                                break;
                            case 0:
                                break;

                        }

                    }
                    login = true;
                    break;
                case 2:
                    while(option != 0){
                        System.out.println("\n*** MENU VENDEDORES ***");
                        option = menu.FirstMenu();
                        switch(option){
                            //Pessoas
                            case 1:
                                optionMenu = menu.personSalesman();
                                switch(optionMenu){
                                    //Cadastrar Cliente
                                    case 1:
                                        peDAO.insertPerson(menu.insertClientSalesman(3));
                                        break;

                                    //Listar Vendedores
                                    case 2:
                                        rs = peDAO.listPerson(2);
                                        menu.listPerson(rs, "Vendedor");
                                        break;

                                    //Listar Clientes
                                    case 3:
                                        rs = peDAO.listPerson(3);
                                        menu.listPerson(rs, "Cliente");
                                        break;
                                    //Editar proprios dados
                                    case 4:
                                        menu.editOwnData(peDAO, email);
                                        break;


                                    //Editar cliente
                                    case 5:
                                        menu.editClient(peDAO);
                                        break;

                                    //Sair
                                    case 0:
                                        System.out.println("voltando ao menu... ");
                                        break;

                                    default:
                                        System.out.println("Opção inválida... ");
                                        break;                       
                                }
                                break;
                                
                            //Produtos
                            case 2:
                                optionMenu = menu.productsSalesman();
                                switch(optionMenu){
                                    //Cadastrar produto
                                    case 1:
                                        poDAO.insertProduct(menu.insertProduct());
                                        break;

                                    //Listar produtos
                                    case 2:
                                        rs = poDAO.listProducts();
                                        menu.listProducts(rs);
                                        break;

                                    //Editar produto
                                    case 3:
                                        menu.editProduct(poDAO);
                                        break;

                                    //Sair
                                    case 0:
                                        System.out.println("voltando ao menu... ");
                                        break;
                                    
                                    default:
                                        System.out.println("Opção inválida... ");

                                }
                                break;

                            //Vendas
                            case 3:
                                optionMenu = menu.salesSalesman();
                                switch(optionMenu){
                                    //Cadastrar venda
                                    case 1:
                                        rs = poDAO.listProducts();
                                        id_sale = menu.insertSale(rs, id_sale, saDAO);


                                        break;
                                    
                                    //Listar vendas
                                    case 2:
                                        rs = saDAO.listSales();
                                        menu.listSales(rs, id_sale);
                                        break;

                                    //Sair
                                    case 0:
                                        System.out.println("voltando ao menu... ");
                                        break;

                                    default:
                                        System.out.println("Opção inválida... ");    

                                }
                                break;

                            //Fornecedor
                            case 4:
                                optionMenu = menu.supplierSalesman();
                                switch(optionMenu){
                                    //Cadastrar supplier
                                    case 1:
                                        suDAO.insertSupplier(menu.insertSupplier());
                                        break;

                                    //Listar supplier
                                    case 2:
                                        rs = suDAO.listSupplier();
                                        menu.listSupplier(rs);
                                        break;
                                    //Editar supplier
                                    case 3:
                                        menu.editSupplier(suDAO);
                                        break;                  
                                    //Sair
                                    case 0:
                                        System.out.println("voltando ao menu... ");
                                        break;
                                    default:
                                        System.out.println("Opção inválida...");   
                                }
                                break;
                            
                            //Fechamento do dia
                            case 5:
                                
                                menu.fechamentoDoDia(ScannerDate, saDAO);
                                break;

                            //Sair
                            case 0:
                                break;

                            default:
                                System.out.println("Opcão inválida... ");
                                break;
                        }
                    }
                    login = true;
                    break;
                case 0:
                    System.out.println("Login ou senha incorretos\n");
                    break;
            }
        }
        sc.close();
        ScannerDate.close();
    }  
}