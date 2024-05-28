import dao.*;
import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.ArrayList;

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
            int option = 1;
            int optionMenu;
            //Qual menu será acessado, Admin ou Vendedor

            switch (menu){
                case 1:
                    while(option != 0) {
                        System.out.println("*** MENU ADMIN ***");
                        option = FirstMenu();
                        switch (option) {
                            //Pessoas
                            case 1:
                                optionMenu = personAdmin();
                                switch (optionMenu) {
                                    //Cadastrar Admin
                                    case 1:
                                        peDAO.insertAdmin(insertAdmin());
                                        break;

                                    //Cadastrar Vendedor
                                    case 2:
                                        peDAO.insertPerson(insertClientSalesman(2));
                                        break;

                                    //Cadastrar Cliente
                                    case 3:
                                        peDAO.insertPerson(insertClientSalesman(3));
                                        break;
                                    //Listar Vendedores
                                    case 4:
                                        rs = peDAO.listPerson(2);
                                        try {
                                            int i = 1;
                                            while (rs.next()) {
                                                System.out.println("\n*** Vendedor " + i + " ***");
                                                System.out.println("CPF: " + rs.getString("cpf"));
                                                System.out.println("Nome: " + rs.getString("first_name") + " " + rs.getString("last_name"));
                                                System.out.println("Email: " + rs.getString("email"));
                                                System.out.println("Data de nascimento: " + rs.getString("birthdate"));
                                                System.out.println("Celular : " + rs.getString("cellphone"));
                                                System.out.println("Endereço: " + rs.getString("address") + " " + rs.getInt("number_address"));
                                                System.out.println(rs.getString("city") + " " + rs.getString("state") + " " + rs.getString("country"));
                                                System.out.print("\n");
                                                i += 1;
                                            }
                                        } catch (SQLException ex) {
                                            ex.printStackTrace();
                                        }
                                        break;
                                    //Listar Clientes
                                    case 5:
                                        rs = peDAO.listPerson(3);
                                        try {
                                            int i = 1;
                                            while (rs.next()) {
                                                System.out.println("\n*** Cliente " + i + " ***");
                                                System.out.println("CPF: " + rs.getString("cpf"));
                                                System.out.println("Nome: " + rs.getString("first_name") + " " + rs.getString("last_name"));
                                                System.out.println("Email: " + rs.getString("email"));
                                                System.out.println("Data de nascimento: " + rs.getString("birthdate"));
                                                System.out.println("Celular : " + rs.getString("cellphone"));
                                                System.out.println("Endereço: " + rs.getString("address") + " " + rs.getInt("number_address"));
                                                System.out.println(rs.getString("city") + " " + rs.getString("state") + " " + rs.getString("country"));
                                                System.out.print("\n");
                                                i += 1;
                                            }
                                        } catch (SQLException ex) {
                                            ex.printStackTrace();
                                        }
                                        break;
                                    //Editar Cliente
                                    case 6:

                                        System.out.print("Digite o CPF do cliente para editar: ");
                                        String cpf = sc.nextLine();
                                        int column = editClient();
                                        if (column == 6) {
                                            System.out.print("Digite a data no modelo YYYY-MM-DD: ");
                                            String date = sc.nextLine();
                                            peDAO.editPersonDate(cpf, date, 3);
                                        } else if (column == 12) {
                                            System.out.print("Digite o número do endereço: ");
                                            int num = sc.nextInt();
                                            peDAO.editPersonInt(cpf, num, 3);
                                        } else {
                                            System.out.println("Digite o novo valor: ");
                                            String string = sc.nextLine();
                                            peDAO.editPersonString(cpf, string, column, 3);
                                        }
                                        break;
                                    //Remover Vendedor
                                    case 7:
                                        peDAO.removeSalesman(removePerson("vendedor"), 2);
                                        break;

                                    //Remover Cliente
                                    case 8:
                                        peDAO.removeClient(removePerson("cliente"), 3);
                                        break;

                                    case 0:
                                        System.out.println("voltando menu... ");
                                        break;
                                    default:
                                        System.out.println("Valor incorreto... ");
                                        break;
                                }
                                break;

                            //Produtos
                            case 2:
                                optionMenu = productsAdmin();
                                switch (optionMenu) {
                                    //Cadastrar produto
                                    case 1:
                                        poDAO.insertProduct(insertProduct());
                                        break;

                                    //Listar produtos
                                    case 2:
                                        rs = poDAO.listProducts();
                                        try{

                                            int i = 1;
                                            while(rs.next()){
                                                System.out.println("\n*** Produto"+i+ " ***");
                                                System.out.println("Id: "+rs.getString("id"));
                                                System.out.println("Nome: "+rs.getString("name_product"));
                                                System.out.println("Descrição: "+rs.getString("description_product"));
                                                System.out.println("Preço: "+ rs.getDouble("price"));
                                                System.out.println("Quantidade no estoque: "+rs.getInt("quantity"));
                                                System.out.println("Nome do fornecedor: "+rs.getString("name_supplier"));
                                                System.out.print("\n");
                                                i += 1;
                                            }
                                        }
                                        catch(SQLException ex){ex.printStackTrace();}
                                        break;

                                    //Editar produtos
                                    case 3:
                                        System.out.print("Digite o id do produto que deseja editar: ");
                                        int id = sc.nextInt();
                                        int column = editProduct();
                                        if(column == 3){
                                            System.out.print("Digite o novo preço: ");
                                            double new_value = sc.nextInt();
                                            poDAO.editProductsDouble(new_value, id);
                                        }
                                        else if(column == 4){
                                            System.out.print("Digite a nova quantidade: ");
                                            int new_value = sc.nextInt();
                                            poDAO.editProductsInt(new_value, id);
                                        }
                                        else{
                                            System.out.print("Digite o novo valor: ");
                                            String new_value = sc.nextLine();
                                            poDAO.editProductsString(id,new_value,column);
                                        }
                                        break;

                                    //Remover produto
                                    case 4:
                                        System.out.print("Digite o id do produto para remover: ");
                                        int id_remove = sc.nextInt();
                                        poDAO.removeProduct(id_remove);
                                        break;

                                    //Sair
                                    case 0:
                                        System.out.println("Voltando ao menu...");
                                        break;

                                    default:
                                        System.out.println("Valor inválido...");
                                        break;
                                }
                                //Vendas
                            case 3:
                                optionMenu = salesAdmin();
                                if(optionMenu == 1){
                                    rs = saDAO.listSales();
                                    listSales(rs);
                                }
                                else if(optionMenu == 0){
                                    break;
                                }
                                else{
                                    System.out.println("Opção inválida...");
                                }

                                break;

                            //Fornecedor
                            case 4:
                                optionMenu = supplierAdmin();
                                switch (optionMenu) {
                                    // insert supplier
                                    case 1:
                                        suDAO.insertSupplier(insertSupplier());
                                        break;
                                    //listar supplier
                                    case 2:
                                        rs = suDAO.listSupplier();
                                        try{
                                            int i = 1;
                                            while(rs.next()){
                                                System.out.println("\n*** Fornecedor " + i + " ***");
                                                System.out.println("CNPJ: " + rs.getString("cnpj"));
                                                System.out.println("Nome: " + rs.getString("name_person") + " " + " representando " + " " + rs.getString("company_name"));
                                                System.out.println("Email: " + rs.getString("email"));
                                                System.out.println("Data de Registro: " + rs.getString("register_date"));
                                                System.out.println("Celular : " + rs.getString("phone_number"));
                                                System.out.println("Endereço: " + rs.getString("address") + " " + rs.getInt("number_address"));
                                                System.out.println(rs.getString("city") + " " + rs.getString("state") + " " + rs.getString("country"));
                                                System.out.print("\n");
                                                i += 1;
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                    //editar supplier
                                    case 3:
                                        System.out.print("Digite o CPF do cliente para editar: ");
                                        String cnpj = sc.nextLine();
                                        int column = editSupplier();
                                        if (column == 11) {
                                            System.out.print("Digite o novo Numero Do Endereço: ");
                                            int new_value = sc.nextInt();
                                            suDAO.editSupplierInt(cnpj, new_value);
                                        }
                                        else{
                                            System.out.println("Digite o novo valor a ser inserido");
                                            String newValue = sc.nextLine();
                                            suDAO.editSupplierString(cnpj,newValue,column);
                                        }
                                        break;
                                    //remove supplier
                                    case 4:
                                        suDAO.removeSupplier(removeSupplier());
                                        break;
                                }
                                break;
                            //Fechamento do dia
                            case 5:
                                System.out.println("Digite a data que deseja do fechamento");
                                System.out.print("no modelo YYYY-MM-DD: ");
                                String date = sc.nextLine();
                                ResultSet rs1 = saDAO.totalOfDay(date);
                                double totalMoney = 0.00;
                                try {
                                    while (rs1.next()) {
                                        totalMoney += rs1.getDouble("total_price");
                                    }
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                                System.out.println("Lucro total do dia " + date + ": " + totalMoney);
                                break;
                            case 0:
                                break;

                        }

                    }
                    login = true;
                    break;
                case 2:
                    System.out.println("*** MENU VENDEDORES ***");
                    option = FirstMenu();
                    switch(option){
                        case 1:
                            continue;
                    }
                    login = true;
                    break;
                case 0:
                    System.out.println("Login ou senha incorretos");
                    break;
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
    public static int editClient(){
        Scanner sc = new Scanner(System.in);
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
        System.out.print("Digite sua opção: ");
        int dado = sc.nextInt();

        return dado;
    }
    public static String removePerson(String person){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o email do "+ person+": ");
        String email = sc.nextLine();
        return email;
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

    public static int supplierAdmin() {
        int option;
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Cadastrar fornecedor: ");
        System.out.println("1. Listar Fornecedor: ");
        System.out.println("1. Editar Fornecedor: ");
        System.out.println("1. Remover Fornecedor: ");
        option = sc.nextInt();
        return option;
    }

    public static Supplier insertSupplier(){
        Scanner sc = new Scanner(System.in);

        String cnpj;
        String companyName;
        String namePerson;
        String email;
        String passwordEmail;
        String phone_number;
        String city;
        String state;
        String country;
        String address;
        int number_address;

        System.out.print("Enter CNPJ: ");
        cnpj = sc.nextLine();

        System.out.print("Enter Company Name: ");
        companyName = sc.nextLine();

        System.out.print("Enter Name of Contact Person: ");
        namePerson = sc.nextLine();

        System.out.print("Enter Email: ");
        email = sc.nextLine();

        System.out.print("Enter Email Password: ");
        passwordEmail = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        phone_number = sc.nextLine();

        System.out.print("Enter City: ");
        city = sc.nextLine();

        System.out.print("Enter State: ");
         state = sc.nextLine();

        System.out.print("Enter Country: ");
        country = sc.nextLine();

        System.out.print("Enter Address: ");
        address = sc.nextLine();

        System.out.print("Enter Address Number: ");
        number_address = sc.nextInt();
        return new Supplier(cnpj,companyName,namePerson,email,passwordEmail,phone_number,city,state,country,address,number_address);
    }

    public static int editSupplier(){
        Scanner sc = new Scanner(System.in);
        System.out.println("*** QUAL DADO DESEJA EDITAR");
        System.out.println("1. CNPJ: ");
        System.out.println("2. Nome Da Compania");
        System.out.println("3. Nome Do Representate");
        System.out.println("4. Email: ");
        System.out.println("5. Senha ");
        System.out.println("6. Numero Do Celular");
        System.out.println("7. Cidade");
        System.out.println("8. Estado");
        System.out.println("9. Pais");
        System.out.println("10. Endereco");
        System.out.println("11. Numero Do Endereço");
        System.out.print("Digite sua opção: ");
        int dado = sc.nextInt();

        return dado;
    }

    public static  String removeSupplier() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o CNPJ do fornecedor que deseja remover: ");
        String cnpj = sc.nextLine();
        return cnpj;
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
        System.out.println("0. Sair: ");
        System.out.print("Digite sua opção: ");
        option = sc.nextInt();
        return option;
    }
    public static int productsAdmin(){
        int option;
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Cadastrar produto: ");
        System.out.println("2. Listar produtos: ");
        System.out.println("3. Editar produtos: ");
        System.out.println("4. Remover produto: ");
        System.out.println("0. Sair: ");
        System.out.print("Digite sua opção: ");
        option = sc.nextInt();
        return option;
    }
    public static int salesAdmin(){
        int option;
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Listar produtos: ");
        System.out.println("0. Sair: ");
        System.out.print("Digite sua opção: ");
        option = sc.nextInt();
        return option;
    }
    public static void listSales(ResultSet rs){
        try{
            int i = 1;
            double total = 0.00;
            boolean start = true;
            boolean restart = false;
            String name_product = "";
            int quantity_product = 0;
            double price_product = 0;
            String payment_method = "";
            System.out.println("-------------------------");
            while(rs.next()){
                if(rs.getInt("id_sale") == i){
                    if(start){
                        System.out.println("*** Venda "+i+" ***");
                        System.out.println("Nome cliente: "+ rs.getString("name_client"));
                        System.out.println("Data: "+rs.getDate("sale_date"));
                        start = false;
                        if(restart){
                            System.out.println(name_product + "  "+ price_product+ "  "+ quantity_product);
                            total += price_product * quantity_product;
                            restart = false;
                        }
                    }
                    System.out.println(rs.getString("name_product") +"  "+ rs.getDouble("price_product")+"  "+ rs.getInt("quantity_product"));
                    total += rs.getInt("quantity_product") * rs.getDouble("price_product");
                    payment_method = rs.getString("payment_method");
                }
                else{
                    System.out.println("Total price: "+ total);
                    System.out.println("Forma de pagamento: "+ payment_method);
                    System.out.println("-------------------------");
                    name_product = rs.getString("name_product");
                    price_product = rs.getDouble("price_product");
                    quantity_product = rs.getInt("quantity_product");
                    total = 0.00;
                    start = true;
                    restart = true;
                    i += 1;
                }
            }
            System.out.println("Total price: "+ total);
            System.out.println("Forma de pagamento: "+ payment_method);
            System.out.println("-------------------------");
        }catch(SQLException ex){ex.printStackTrace();}
    }


    public static Products insertProduct(){
        String name;
        String description;
        double price;
        int quantity;
        String name_supplier;
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o nome do produto: ");
        name = sc.nextLine();
        System.out.print("Escreva a descrição do produto: ");
        description = sc.nextLine();
        System.out.print("Digite o preço");
        price = sc.nextDouble();
        System.out.print("Digite a quantidade: ");
        quantity = sc.nextInt();
        System.out.print("Digite o nome do fornecedor: ");
        name_supplier = sc.nextLine();
        return new Products(name,description,price,quantity,name_supplier);
    }
    public static int editProduct(){
        Scanner sc = new Scanner(System.in);
        int dado;
        System.out.println("*** QUAL DADO VOCÊ DESEJA EDITAR");
        System.out.println("1. Nome do produto: ");
        System.out.println("2. Descrição do produto: ");
        System.out.println("3. Preço: ");
        System.out.println("4. Quantidade no estoque: ");
        System.out.println("5. Nome do fornecedor: ");
        System.out.print("Digite sua opção: ");
        dado = sc.nextInt();
        return dado;
    }
}

