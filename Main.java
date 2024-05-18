
import dao.*;
import model.*;

import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;

public class Main {

    public static void main(String args[]){
        PersonDAO pDAO = new PersonDAO();
        SupplierDAO sDAO = new SupplierDAO();



        //Supplier sp = new Supplier("03254085000122", "ProdutoraMT", "Tarso Bertolini",
                //"tarsinho@outlook.com", "123456", "41992730411", "Curitiba",
                //"Paraná", "Brasil", "Rua Manoel Eufrásio", 480);

        //Scanner sc = new Scanner(System.in);
        //System.out.println("Digite o email para remover");
        //String email = sc.nextLine();


        ResultSet rs = sDAO.listSupplier();
        try{
            int i = 1;
            while(rs.next()){

                System.out.println("*** Supplier "+ i+" ***");
                System.out.println("Number_address : "+ rs.getString("number_address"));
                i += 1;
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        System.out.println("rodou");



    }
}
