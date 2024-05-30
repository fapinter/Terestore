package dao;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Products;

public class ProductsDAO {
    private Conexao conexao;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;

    public ProductsDAO(){conexao = Conexao.getConexao();}

    public void insertProduct(Products p){
        this.query = "INSERT INTO products(name_product, description_product,price,quantity,name_supplier) VALUES(?,?,?,?,?)";

        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,p.getName_product());
            this.ps.setString(2,p.getDescription_product());
            this.ps.setDouble(3,p.getPrice());
            this.ps.setInt(4,p.getQuantity());
            this.ps.setString(5,p.getName_supplier());
            this.ps.executeUpdate();
            this.ps.close();
        }
        catch (SQLException ex){ex.printStackTrace();}
    }
    public ResultSet listProducts(){
        this.query = "SELECT * FROM products";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.rs = ps.executeQuery();
        }
        catch(SQLException ex){ex.printStackTrace();}
        return this.rs;
    }
    public void removeProduct(int id){
        this.query = "DELETE FROM products WHERE id=?";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setInt(1, id);
            this.ps.executeUpdate();
            this.ps.close();
        }
        catch(SQLException ex){ex.printStackTrace();}
    }

    public void editProductsString(int id, String value, int COLUMN){
        if(COLUMN == 1){this.query = "UPDATE products SET name_product = ? WHERE id = ?";}
        else if (COLUMN == 2){this.query = "UPDATE products SET description_product = ? WHERE id = ?";}
        else if (COLUMN == 5){this.query = "UPDATE products SET name_supplier = ? WHERE id = ?";}
        else{
            System.out.println("Coluna nao identificada");
            return;
        }
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,value);
            this.ps.setInt(2,id);
            this.ps.executeUpdate();
            this.ps.close();
        }
        catch(SQLException ex){ex.printStackTrace();}
    }

    public void editProductsDouble(double newPrice, int id) {
        String query = "UPDATE products SET price = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = conexao.getConnection().prepareStatement(query);
            ps.setDouble(1, newPrice);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void editProductsInt(int newQuantity, int id) {
        String query = "UPDATE products SET quantity = ? WHERE id = ?";
        PreparedStatement ps = null;

        try {
            ps = conexao.getConnection().prepareStatement(query);
            ps.setInt(1, newQuantity);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
