package dao;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    public void removeSupplier(String cnpj){
        this.query = "DELETE FROM supplier WHERE cnpj=?";

        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,cnpj);
            this.ps.executeUpdate();
            this.ps.close();
        }
        catch(SQLException ex){ex.printStackTrace();}
    }
    public ResultSet getSupplier(String cnpj){
        this.query = "SELECT * FROM supplier WHERE cnpj=?";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,cnpj);
            this.rs = this.ps.executeQuery();
        }
        catch(SQLException ex){ex.printStackTrace();}
        return this.rs;
    }

    public void editSupplierString(String cnpj, String value, int COLUMN){
        if(COLUMN == 1){this.query = "UPDATE supplier SET cnpj= ? WHERE cnpj=?";}
        else if (COLUMN == 2){this.query = "UPDATE supplier SET company_name= ? WHERE cnpj=?";}
        else if (COLUMN == 3){this.query = "UPDATE supplier SET name_person= ? WHERE cnpj=?";}
        else if (COLUMN == 4){this.query = "UPDATE supplier SET email= ? WHERE cnpj=?";}
        else if (COLUMN == 5){this.query = "UPDATE supplier SET password_email= ? WHERE cnpj=?";}
        else if (COLUMN == 6){this.query = "UPDATE supplier SET phone_number= ? WHERE cnpj=?";}
        else if (COLUMN == 7){this.query = "UPDATE supplier SET city= ? WHERE cnpj=?";}
        else if (COLUMN == 8){this.query = "UPDATE supplier SET state= ? WHERE cnpj=?";}
        else if (COLUMN == 9){this.query = "UPDATE supplier SET country= ? WHERE cnpj=?";}
        else if (COLUMN == 10){this.query = "UPDATE supplier SET address= ? WHERE cnpj=?";}
        else{
            System.out.println("Coluna nao identificada");
            return;
        }
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,value);
            this.ps.setString(2,cnpj);
            this.ps.executeUpdate();
            this.ps.close();
        }
        catch(SQLException ex){ex.printStackTrace();}
    }
    public void editSupplierInt(String cnpj, int value){
        this.query = "UPDATE supplier SET number_address = ? WHERE cnpj = ?";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setInt(1,value);
            this.ps.setString(2,cnpj);
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
