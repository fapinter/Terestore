package dao;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
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

    public void insertSale(Sales sl, int type_sale){
        //Credito
        if(type_sale == 1){
            this.query = "INSERT INTO sales(id_sale,name_client,active_client,name_product,price_product,quantity_product,payment_method,portions,sale_date) VALUES(?,?,1,?,?,?,?,?, CURDATE())";
        }
        //Debito ou dinheiro
        else{
            this.query = "INSERT INTO sales(id_sale,name_client,active_client,name_product,price_product,quantity_product,payment_method,sale_date) VALUES(?,?,1,?,?,?,?, CURDATE())";
        }
        
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setInt(1, sl.getId_sale());
            this.ps.setString(2,sl.getName_client());
            this.ps.setString(3, sl.getName_product());
            this.ps.setDouble(4,sl.getPrice_product());
            this.ps.setInt(5,sl.getQuantity_product());
            this.ps.setString(6, sl.getPayment_method());
            if(type_sale == 1){
                this.ps.setInt(7, sl.getPortions());
            }
            
            this.ps.executeUpdate();
            this.ps.close();
        }
        catch (SQLSyntaxErrorException ex){System.out.println("Erro na sintaxe MySQL, verifique o código MySQL");}
        catch(SQLException ex){System.out.println("Erro: conexão com o banco de dados, verifique sua senha e/ou usuário");}
    }

    public ResultSet listSales(){
        this.query = "SELECT * FROM sales";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.rs = this.ps.executeQuery();

        }
        catch (SQLSyntaxErrorException ex){System.out.println("Erro na sintaxe MySQL, verifique o código MySQL");}
        catch(SQLException ex){System.out.println("Erro: conexão com o banco de dados, verifique sua senha e/ou usuário");}
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
        catch (SQLSyntaxErrorException ex){System.out.println("Erro na sintaxe MySQL, verifique o código MySQL");}
        catch (SQLException ex){System.out.println("Erro: conexão com o banco de dados, verifique sua senha e/ou usuário"); }
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
        catch (NullPointerException ex){
            System.out.println("Ponteiro retornado nulo");
        }
        catch (SQLSyntaxErrorException ex){System.out.println("Erro na sintaxe MySQL, verifique o código MySQL");}
        catch(SQLException ex){System.out.println("Erro: conexão com o banco de dados, verifique sua senha e/ou usuário");}
        return id + 1;
    }
    public void updateIDsale(){
        this.query = "UPDATE id_sales SET id_sale = id_sale + 1";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.executeUpdate();
        }
        catch (SQLSyntaxErrorException ex){System.out.println("Erro na sintaxe MySQL, verifique o código MySQL");}
        catch(SQLException ex){System.out.println("Erro: conexão com o banco de dados, verifique sua senha e/ou usuário");}

    }



}
