import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Scanner;

import dao.PersonDAO;
import dao.ProductsDAO;
import dao.SalesDAO;
import dao.SupplierDAO;
import model.Person;
import model.Products;
import model.Sales;
import model.Supplier;

import javax.swing.*;

public class Menu {

    PersonDAO personDAO = new PersonDAO();
    SupplierDAO supplierDAO = new SupplierDAO();

    //Menu principal, ambos admin e vendedor o utilizam
    public int FirstMenu(){
            System.out.println("1. Pessoas: ");
            System.out.println("2. Produto: ");
            System.out.println("3. Vendas: ");
            System.out.println("4. Fornecedor: ");
            System.out.println("5. Fechamento do dia: ");
            System.out.println("0. Sair");
            System.out.print("Digite sua opção: ");


        return Validation.validationIntMenu(0, 5);

    }
    //Menu de pessoas do admin
    public int personAdmin(){

        System.out.println("\n*** MENU PESSOAS ***");
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

        return Validation.validationIntMenu(0, 8);

    }
    //Menu de produtos do admin
    public int productsAdmin(){

        System.out.println("\n*** MENU PRODUTOS ***");
        System.out.println("1. Cadastrar produto: ");
        System.out.println("2. Listar produtos: ");
        System.out.println("3. Editar produtos: ");
        System.out.println("4. Remover produto: ");
        System.out.println("0. Sair: ");
        System.out.print("Digite sua opção: ");

        return Validation.validationIntMenu(0, 4);

    }
    //Menu de fornecedor do admin
    public int supplierAdmin() {

        System.out.println("\n*** MENU FORNECEDOR ***");
        System.out.println("1. Cadastrar fornecedor: ");
        System.out.println("2. Listar Fornecedor: ");
        System.out.println("3. Editar Fornecedor: ");
        System.out.println("4. Remover Fornecedor: ");
        System.out.println("0. Sair: ");
        System.out.print("Digite sua opção: ");

        return Validation.validationIntMenu(0, 4);

    }
    //Menu de vendas do admin
    public int salesAdmin(){

        System.out.println("\n*** MENU VENDAS ***");
        System.out.println("1. Listar vendas: ");
        System.out.println("0. Sair: ");
        System.out.print("Digite sua opção: ");


        return Validation.validationIntMenu(0, 1);
    }
    //Menu de pessoas do vendedor
    public int personSalesman(){

        System.out.println("\n*** MENU PESSOAS ***");
        System.out.println("1. Cadastrar Cliente: ");
        System.out.println("2. Listar Vendedores: ");
        System.out.println("3. Listar Clientes: ");
        System.out.println("4. Editar seus dados: ");
        System.out.println("5. Editar CLiente: ");
        System.out.println("0. Sair: ");
        System.out.print("Digite sua opção: ");

        return Validation.validationIntMenu(0, 5);
    }
    //Menu de produtos do vendedor
    public int productsSalesman(){

        System.out.println("\n*** MENU PRODUTOS ***");
        System.out.println("1. Cadastrar produto: ");
        System.out.println("2. Listar produtos: ");
        System.out.println("3. Editar produtos: ");
        System.out.println("0. Sair: ");
        System.out.print("Digite sua opção: ");

        return Validation.validationIntMenu(0, 3);

    }
    //Menu de vendas do vendedor
    public int salesSalesman(){

        System.out.println("\n*** MENU VENDAS ***");
        System.out.println("1. Cadastrar venda: ");
        System.out.println("2. Listar vendas: ");
        System.out.println("0. Sair: ");
        System.out.print("Digite sua opção: ");

        return Validation.validationIntMenu(0, 2);

    }
    //Menu de fornecedor do vendedor
    public int supplierSalesman(){

        System.out.println("\n*** MENU FORNECEDOR ***");
        System.out.println("1. Cadastrar fornecedor: ");
        System.out.println("2. Listar fornecedores: ");
        System.out.println("3. Editar fornecedor: ");
        System.out.println("0. Sair: ");

        return Validation.validationIntMenu(0,3);
    }





    public  Person insertAdmin(){
        Scanner sc = new Scanner(System.in);
        String cpf;
        String first_name;
        String email;
        String password;

        //Fazer verificação das entradas

        System.out.print("Digite o CPF: ");
        cpf = Validation.validateCPF(sc, personDAO);
        System.out.print("Digitie o primeiro nome: ");
        first_name = Validation.validationString(sc);
        System.out.print("Digite o email: ");
        email = Validation.validateEmail(sc, 2);
        System.out.print("Digite a senha: ");
        password = sc.nextLine();

        return new Person(cpf,first_name, email, password, 1);
    }

    public  Person insertClientSalesman(int type_person){
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
        System.out.print("Digite o cpf: ");
        cpf = Validation.validateCPF(sc, personDAO);
        System.out.print("Digite o primeiro nome: ");
        first_name = Validation.validationString(sc);
        System.out.print("Digite o sobrenome: ");
        last_name = Validation.validationString(sc);
        System.out.print("Digite o email: ");
        email = Validation.validateEmail(sc, 2);
        System.out.print("Digite a senha: ");
        password = sc.nextLine();
        System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
        birthdate = Validation.validateDate(sc);
        System.out.print("Digite o número de celular: ");
        cellphone = Validation.validateStringInt(sc);
        System.out.print("Digite a cidade: ");
        city = Validation.validationString(sc);
        System.out.print("Digite o estado: ");
        state = Validation.validationString(sc);
        System.out.print("Digite o país: ");
        country = Validation.validationString(sc);
        System.out.print("Digite o endereço(sem o número): ");
        address = Validation.validationString(sc);
        System.out.print("Digite o numero do endereço: ");
        number_address = Validation.validationInt();


        return new Person(cpf,first_name,email,password, type_person,
        last_name,birthdate,cellphone,city,state,country,address,number_address);
    }

    public  Products insertProduct(){
        Scanner scString = new Scanner(System.in);Scanner sc = new Scanner(System.in);
        String name;
        String description;

        int quantity;
        String name_supplier;


        System.out.print("Digite o nome do produto: ");
        name = Validation.validationString(scString);
        System.out.print("Escreva a descrição do produto: ");
        description = Validation.validationString(scString);
        System.out.print("Digite o preço: ");
        double price = Validation.validationDouble(sc);
        System.out.print("Digite a quantidade: ");
        quantity = Validation.validationInt();
        System.out.print("Digite o nome do fornecedor: ");
        name_supplier = Validation.validationString(scString);


        return new Products(name,description,price,quantity,name_supplier);
    }

    public int insertSale(ResultSet rs, int id_sale, SalesDAO saDAO){
        Scanner sc = new Scanner(System.in);

        String name;
        double totalPrice = 0.00;
        int payment_method;
        String paymentString = "";
        int id_product = 1;
        int parcelas = 0;
        //ID and Name
        Hashtable <Integer, String> name_table = new Hashtable<Integer, String>();
        //ID and price
        Hashtable <Integer, Double> price_table = new Hashtable<Integer, Double>();
        //ID and quantity
        Hashtable <Integer, Integer> All_products = new Hashtable<Integer,Integer>();
        System.out.print("Digite o nome do cliente: ");
        name = Validation.validationString(sc);
        try{
            while(rs.next()){
                name_table.put(rs.getInt("id"), rs.getString("name_product"));
                price_table.put(rs.getInt("id"), rs.getDouble("price"));
            }

        }
        catch(SQLException ex){System.out.println("Erro ao coletar dados dos produtos, verifique a conexão com o"+
            "banco de dados");}
        while(id_product != 0){
            System.out.println("--------------------------------");
            for(int i = 1; i < name_table.size() + 1; i++){
                System.out.println("ID: "+i+" Produto: "+ name_table.get(i));
            }
            System.out.println("--------------------------------");
            System.out.println("0. Finalizar Cadastro: ");
            System.out.print("Digite sua opção: ");
            id_product = Validation.validationInt();
            if(id_product != 0){
                System.out.print("Quantas unidades do produto: ");
                int quantidade = Validation.validationInt();
                totalPrice += price_table.get(id_product) * quantidade;
                All_products.put(id_product, quantidade);
            }
        }
        System.out.println("Qual será o método de pagamento? ");
        System.out.println("1. Crédito: ");
        System.out.println("2. Débito: ");
        System.out.println("3. Dinheiro: ");
        System.out.print("Digite sua opção: ");
        payment_method = Validation.validationIntMenu(0, 3);
        switch(payment_method){
            case 1:
                paymentString = "Credito";
                break;
            case 2:
                paymentString = "Debito";
                break;
            case 3:
                paymentString = "Dinheiro";
                break;

            default:
                System.out.println("ERRO: Opção inválida...");
                break;
        }
        if(payment_method == 1){
            if(totalPrice > 1000.00){
                System.out.println("-----------------------------");
                for(int i = 1; i < 6; i++){
                    System.out.println("Parcelar em "+i+ "x : "+ totalPrice/i);
                }
                for(int i = 6; i < 11; i++){
                    System.out.println("Parcelar em "+i+ "x : "+ (totalPrice + totalPrice*0.05)/i);
                }
                System.out.println("-----------------------------");
                System.out.print("Em quantas vezes deseja parcelar: ");
                parcelas = Validation.validationIntMenu(0, 11);

                for(Integer key : All_products.keySet()){

                    saDAO.insertSale(new Sales(id_sale, name, 1,
                    name_table.get(key),price_table.get(key), All_products.get(key), paymentString, parcelas),1);
                }
            }
            else{
                System.out.println("-----------------------------");
                for(int i = 1; i < 6; i++){
                    System.out.println("Parcelar em "+i+ "x : "+ totalPrice/i);
                }
                System.out.println("-----------------------------");
                System.out.print("Em quantas vezes deseja parcelar: ");
                parcelas = Validation.validationIntMenu(1, 6);

                for(Integer key : All_products.keySet()){

                    saDAO.insertSale(new Sales(id_sale, name, 1,
                    name_table.get(key),price_table.get(key), All_products.get(key), paymentString, parcelas),1);
                }
            }
        }
        else if(payment_method == 2 || payment_method == 3){
            for(Integer key : All_products.keySet()){

                saDAO.insertSale(new Sales(id_sale, name, 1,
                name_table.get(key),price_table.get(key), All_products.get(key), paymentString),2);
            }
        }
        saDAO.updateIDsale();
        return id_sale + 1;
    }

    public  Supplier insertSupplier(){
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

        System.out.print("Digite o CNPJ: ");
        cnpj = Validation.validateStringInt(sc);
        System.out.print("Digite o nome da empresa: ");
        companyName = Validation.validationString(sc);
        System.out.print("Digite o nome da pessoa: ");
        namePerson = Validation.validationString(sc);
        System.out.print("Digite o email: ");
        email = Validation.validateEmail(sc, 2);
        System.out.print("Digite a senha: ");
        passwordEmail = sc.nextLine();
        System.out.print("Digite o número de celular: ");
        phone_number = Validation.validateStringInt(sc);
        System.out.print("Digite a cidade: ");
        city = Validation.validationString(sc);
        System.out.print("Digite o estado: ");
         state = Validation.validationString(sc);
        System.out.print("Digite o país: ");
        country = Validation.validationString(sc);
        System.out.print("Digite o endereço(sem o número): ");
        address = Validation.validationString(sc);
        System.out.print("Digite o número do endereço: ");
        number_address = Validation.validationInt();


        return new Supplier(cnpj,companyName,namePerson,email,passwordEmail,phone_number,city,state,country,address,number_address);
    }


    public void listPerson(ResultSet rs, String person){
        try {
            int i = 1;

            while (rs.next()) {
                System.out.println("\n*** "+person+" " + i + " ***");
                System.out.println("CPF: " + rs.getString("cpf"));
                System.out.println("Nome: " + rs.getString("first_name")
                 + " " + rs.getString("last_name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Data de nascimento: " + rs.getString("birthdate"));
                System.out.println("Celular : " + rs.getString("cellphone"));
                System.out.println("Endereço: " + rs.getString("address")
                 + " " + rs.getInt("number_address"));
                System.out.println(rs.getString("city") +
                 " " + rs.getString("state") + " " + rs.getString("country"));
                System.out.print("\n");
                i += 1;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao coletar dados dos usuários, verifique a conexão com o banco de dados");
        }
    }
    public  void listProducts(ResultSet rs){
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
        catch(SQLException ex){System.out.println("Erro ao coletar dados dos produtos, verifique a conexão com o banco de dados");}
    }

    public  void listSales(ResultSet rs, int id_sale){
        try{

            int current = 0;
            int prev = 0;
            double total = 0.00;
            boolean started = false;
            String name_product = "";
            int quantity_product = 0;
            double price_product = 0;
            String payment_method = "";
            while(rs.next()){
                current = rs.getInt("id_sale");
                if (current != prev){
                    if (started){
                        System.out.println("Total price: "+ total);
                        System.out.println("Forma de pagamento: "+ payment_method);

                        total = 0.00;
                        payment_method = "";
                        started = false;
                    }
                    System.out.println("-------------------");
                    System.out.println("Venda "+ current);
                    System.out.println("Nome cliente: "+ rs.getString("name_client"));
                    System.out.println("Data: "+rs.getDate("sale_date"));
                    name_product = rs.getString("name_product");
                    price_product = rs.getDouble("price_product");
                    quantity_product = rs.getInt("quantity_product");
                    payment_method = rs.getString("payment_method");
                    System.out.println(name_product + "  "+ price_product+ "  "+ quantity_product);
                    total += price_product * quantity_product;
                    prev = current;
                    started = true;

                }
                else{
                    name_product = rs.getString("name_product");
                    price_product = rs.getDouble("price_product");
                    quantity_product = rs.getInt("quantity_product");
                    payment_method = rs.getString("payment_method");
                    System.out.println(name_product + "  "+ price_product+ "  "+ quantity_product);
                    total += price_product * quantity_product;
                    prev = current;
                    started = true;
                }
            }
            if(current == (id_sale-1)){
                System.out.println("Total price: "+ total);
                System.out.println("Forma de pagamento: "+ payment_method);
                System.out.println("-------------------------");
            }
        }catch(SQLException ex){System.out.println("Erro ao coletar dados das vendas, verifique a conexão com o banco de dados");}
    }
    public  void listSupplier(ResultSet rs){
        try{

            int i = 1;
            while(rs.next()){
                System.out.println("\n*** Fornecedor " + i + " ***");
                System.out.println("CNPJ: " + rs.getString("cnpj"));
                System.out.println("Nome: " + rs.getString("name_person") +
                 " " + " representando " + " " + rs.getString("company_name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Data de Registro: " + rs.getString("register_date"));
                System.out.println("Celular : " + rs.getString("phone_number"));
                System.out.println("Endereço: " + rs.getString("address") +
                 " " + rs.getInt("number_address"));
                System.out.println(rs.getString("city") + " "
                 + rs.getString("state") + " " + rs.getString("country"));
                System.out.print("\n");
                i += 1;
            }
        }catch (SQLException ex){System.out.println("Erro ao coletar dados dos fornecedores, verifique a conexão com o banco de dados");}
    }
    public  void editClient(PersonDAO peDAO){
        Scanner scString = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);
        Scanner scDate = new Scanner(System.in);

        System.out.print("Digite o CPF do Cliente para editá-lo: ");
        String cpf = Validation.validateCPF(scInt, personDAO);
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
        int column = Validation.validationIntMenu(1, 12);

        if (column == 6) {
            System.out.print("Digite a data no modelo YYYY-MM-DD: ");
            String date = Validation.validateDate(scDate);
            peDAO.editPersonDate(cpf, date, 3);
        } else if (column == 12) {
            System.out.print("Digite o número do endereço: ");
            int num = Validation.validationInt();
            peDAO.editPersonInt(cpf, num, 3);
        } else if (column == 1 || column == 3 || column == 4 || column == 7) {
            System.out.print("Digite o novo valor: ");
            String string = Validation.validateStringInt(scString);
            peDAO.editPersonString(cpf, string, column, 3);
        } else {
            System.out.print("Digite o novo valor: ");
            String string = Validation.validationString(scString);
            peDAO.editPersonString(cpf, string, column, 3);
        }

    }
    public  void editOwnData(PersonDAO peDAO, String email){
        Scanner sc = new Scanner(System.in);
        Scanner scNewValue = new Scanner(System.in);

        String cpf = peDAO.getCPF(email);
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
        int column = Validation.validationIntMenu(1, 12);

        if (column == 6) {
            System.out.print("Digite a data no modelo YYYY-MM-DD: ");
            String date = Validation.validateDate(sc);
            peDAO.editPersonDate(cpf, date, 3);
        } else if (column == 12) {
            System.out.print("Digite o número do endereço: ");
            int num = Validation.validationInt();
            peDAO.editPersonInt(cpf, num, 3);
        } else if (column == 1 || column == 3 || column == 4 || column == 7) {
            System.out.print("Digite o novo valor: ");
            String string = Validation.validateStringInt(scNewValue);
            peDAO.editPersonString(cpf, string, column, 3);
        } else {
            System.out.print("Digite o novo valor: ");
            String string = Validation.validationString(scNewValue);
            peDAO.editPersonString(cpf, string, column, 3);
        }

    }
    public  void editProduct(ProductsDAO poDAO){
        Scanner sc = new Scanner(System.in);
        Scanner scNewValue = new Scanner(System.in);

        System.out.print("Digite o id do produto que deseja editar: ");
        int id = sc.nextInt();
        System.out.println("*** QUAL DADO VOCÊ DESEJA EDITAR");
        System.out.println("1. Nome do produto: ");
        System.out.println("2. Descrição do produto: ");
        System.out.println("3. Preço: ");
        System.out.println("4. Quantidade no estoque: ");
        System.out.println("5. Nome do fornecedor: ");
        System.out.print("Digite sua opção: ");
        int column = Validation.validationIntMenu(1, 5);

        if (column == 3) {
            System.out.print("Digite o novo preço: ");
            double new_value = Validation.validationDouble(scNewValue);
            poDAO.editProductsDouble(new_value, id);
        } else if (column == 4) {
            System.out.print("Digite a nova quantidade: ");
            int new_value = Validation.validationInt();
            poDAO.editProductsInt(new_value, id);
        } else {
            System.out.print("Digite o novo valor: ");
            String new_value = Validation.validationString(scNewValue);
            poDAO.editProductsString(id, new_value, column);
        }

    }


    public void editSupplier(SupplierDAO suDAO){
        Scanner sc = new Scanner(System.in);
        Scanner scNewValue = new Scanner(System.in);

        System.out.print("Digite o CNPJ do fornecedor para editar: ");
        String cnpj = Validation.validateCNPJ(sc,supplierDAO);
        System.out.println("*** QUAL DADO DESEJA EDITAR");
        System.out.println("1. CNPJ: ");
        System.out.println("2. Nome Da Empresa");
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
        int column = Validation.validationIntMenu(1, 11);

        if (column == 11) {
            System.out.print("Digite o novo número do endereço: ");
            int new_value = Validation.validationInt();
            suDAO.editSupplierInt(cnpj, new_value);
        } else {
            System.out.print("Digite o novo valor a ser inserido: ");
            String newValue = Validation.validationString(scNewValue);
            suDAO.editSupplierString(cnpj, newValue, column);
        }

    }

    public  String removePerson(String person){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o email do "+ person+": ");
        String email = Validation.validateEmail(sc, 1);

        return email;
    }
    public  void removeProduct(ProductsDAO poDAO){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o id do produto para remover: ");
        int id_remove = Validation.validationInt();

        poDAO.removeProduct(id_remove);
    }

    public   void removeSupplier(SupplierDAO suDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o CNPJ do fornecedor que deseja remover: ");
        String cnpj = Validation.validateCNPJ(sc,supplierDAO);

        suDAO.removeSupplier(cnpj);
    }
    public  void fechamentoDoDia(Scanner ScannerDate, SalesDAO saDAO){
        String date = "";
        double totalMoney= 0.00;
        try {
            System.out.println("Digite a data que deseja do fechamento");
            System.out.print("no modelo YYYY-MM-DD: ");
            date = ScannerDate.nextLine();
            ResultSet rs = saDAO.totalOfDay(date);
            while (rs.next()) {
                totalMoney += rs.getDouble("total_price");
            }
        }
        catch (SQLException ex) {
            System.out.print("Entrada incorreta de Data");
        }

        System.out.println("\n************************\n");
        System.out.println("Lucro total do dia " + date + ": " + totalMoney);
        System.out.println("\n************************\n");
    }

}