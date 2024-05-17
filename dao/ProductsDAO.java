package dao;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Products;

import javax.xml.transform.Result;

public class ProductsDAO {
    private Conexao conexao;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;

    public ProductsDAO(){conexao = Conexao.getConexao();}

    public void insertProduct(Products p){
        this.query = "INSERT INTO products VALUES(?,?,?,?,?,?)";

        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,p.getName_product());
            this.ps.setString(2,p.getDescription_product());
            this.ps.setDouble(3,p.getPrice());
            this.ps.setInt(4,p.getQuantity());
            this.ps.setString(5,p.getName_supplier());
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
}
