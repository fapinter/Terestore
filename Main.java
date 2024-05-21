
import dao.*;
import model.*;

import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;

public class Main {

    public static void main(String args[]){
        PersonDAO pDAO = new PersonDAO();
        SupplierDAO sDAO = new SupplierDAO();
        SalesDAO slDAO = new SalesDAO();



        //Supplier sp = new Supplier("03254085000122", "ProdutoraMT", "Tarso Bertolini",
                //"tarsinho@outlook.com", "123456", "41992730411", "Curitiba",
                //"Paraná", "Brasil", "Rua Manoel Eufrásio", 480);

        //Scanner sc = new Scanner(System.in);
        //System.out.println("Digite o email para remover");
        //String email = sc.nextLine();
        ResultSet rs1 = slDAO.totalOfDay("2024-05-21", "Dinheiro");
        ResultSet rs2 = slDAO.totalOfDay("2024-05-21", "Debito");
        double cash = 0.00;
        double debit = 0.00;
        try{
            while(rs1.next()){
                System.out.println("Cash Total price is: "+ rs1.getDouble("total_price"));
                cash += rs1.getDouble("total_price");
            }
            while(rs2.next()){
                System.out.println("Debit Total price is: "+ rs2.getDouble("total_price"));
                debit += rs2.getDouble("total_price");
            }

        }
        catch(SQLException ex){ex.printStackTrace();}

        System.out.println("Fechamento do dia em Dinheiro: "+ cash);
        System.out.println("Fechamento do dia em Debito: "+ debit);

        System.out.println("rodou");
    }
}
