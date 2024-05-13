package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import model.Person;
import model.Supplier;


public class SupplierDAO {
    private Conexao conexao;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;

    public SupplierDAO(){conexao = Conexao.getConexao();}

    public void insertSupplier(Supplier sp){
        this.query = "INSERT INTO supplier VALUES(?,?,?,?,?,?,?,?,?,?,?, CURDATE())";

        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            valuesQuery(sp);
            this.ps.executeUpdate();
            this.ps.close();

        } catch(SQLException ex){ex.printStackTrace();}
    }

    public ResultSet listSupplier(){
        this.query = "SELECT * FROM supplier";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.rs = this.ps.executeQuery();
        }
        catch(SQLException ex){ex.printStackTrace();}
        return this.rs;
    }
    public void removeSupplier(String email){
        this.query = "DELETE FROM supplier WHERE email=?";

        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,email);
            this.ps.executeUpdate();
            this.ps.close();
        }
        catch(SQLException ex){ex.printStackTrace();}
    }
    public ResultSet getSupplier(String email){
        this.query = "SELECT * FROM supplier WHERE email=?";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,email);
            this.rs = this.ps.executeQuery();
        }
        catch(SQLException ex){ex.printStackTrace();}
        return this.rs;
    }

    public void editPersonString(String prev_value, String value, String COLUMN){
        this.query = "UPDATE supplier SET ?=? WHERE ?=?";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,COLUMN);
            this.ps.setString(2,value);
            this.ps.setString(3,COLUMN);
            this.ps.setString(4,prev_value);
            this.ps.executeUpdate();
            this.ps.close();

        }
        catch(SQLException ex){ex.printStackTrace();}
    }
    public void editPersonInt(int prev_value, int value, String COLUMN){
        this.query = "UPDATE supplier SET ?=? WHERE ?=?";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,COLUMN);
            this.ps.setInt(2,value);
            this.ps.setString(3,COLUMN);
            this.ps.setInt(4,prev_value);
            this.ps.executeUpdate();
            this.ps.close();
        }
        catch(SQLException ex){ex.printStackTrace();}
    }


    public void valuesQuery(Supplier sp){
        try{
            this.ps.setString(1,sp.getCnpj());
            this.ps.setString(2,sp.getCompany_name());
            this.ps.setString(3,sp.getName_person());
            this.ps.setString(4,sp.getEmail());
            this.ps.setString(5,sp.getPassword_email());
            this.ps.setString(6,sp.getPhone_number());
            this.ps.setString(7,sp.getCity());
            this.ps.setString(8,sp.getState());
            this.ps.setString(9,sp.getCountry());
            this.ps.setString(10,sp.getAddress());
            this.ps.setInt(11,sp.getNumber_address());
        }
        catch(SQLException ex){ex.printStackTrace();}
    }
}
