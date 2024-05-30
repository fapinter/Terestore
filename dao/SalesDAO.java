package dao;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Sales;

public class SalesDAO {

    private Conexao conexao;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;

    public SalesDAO(){conexao = Conexao.getConexao();}

    public void insertSale(Sales sl){
        this.query = "INSERT INTO sales VALUES(?,?,1,?,?,?,?, CURDATE())";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setInt(1, sl.getId_sale());
            this.ps.setString(2,sl.getName_client());
            this.ps.setString(3, sl.getName_product());
            this.ps.setDouble(4,sl.getPrice_product());
            this.ps.setInt(5,sl.getQuantity_product());
            this.ps.setString(6, sl.getPayment_method());
            this.ps.executeUpdate();
            this.ps.close();
        }
        catch (SQLException ex){ex.printStackTrace();}
    }

    public ResultSet listSales(){
        this.query = "SELECT * FROM sales";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.rs = this.ps.executeQuery();

        }
        catch(SQLException ex){ex.printStackTrace();}
        return this.rs;
    }

    public ResultSet totalOfDay(String date){
        this.query = "SELECT (price_product * quantity_product) as 'total_price' FROM sales WHERE sale_date =? AND payment_method != ? ";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1, date);
            this.ps.setString(2, "Credito");
            this.rs = this.ps.executeQuery();

        }
        catch (SQLException ex){
            System.out.println("Entrada incorreta de data...");
            
        }
        catch(InputMismatchException IMEx){
            System.out.print("Digite a data novamente: ");
        }
        return this.rs;
    }
    public int getIDsale(){
        int id = 0;
        this.query = "SELECT id_sale FROM id_sales";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.rs = this.ps.executeQuery();
            this.rs.next();
            id = this.rs.getInt("id_sale");
        }
        catch(SQLException ex){ex.printStackTrace();}
        return id + 1;
    }
    public void updateIDsale(){
        this.query = "UPDATE id_sales SET id_sale = id_sale + 1";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.executeUpdate();
        }
        catch(SQLException ex){ex.printStackTrace();}
    }



}
