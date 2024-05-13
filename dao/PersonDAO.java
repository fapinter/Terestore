package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Person;
public class PersonDAO {

    private Conexao conexao;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;

    public PersonDAO(){conexao = Conexao.getConexao();}

    public void insertPerson(Person p, int type_person){//Type_person será usado para o menu, para evitar ter que digitar o tipo da pessoa
        this.query = "INSERT INTO person VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?, CURDATE())";

        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            valuesQuery(p, type_person);
            this.ps.executeUpdate();
            this.ps.close();
        }
        catch (SQLException ex){ex.printStackTrace();}

    }
    public ResultSet listPerson(int type){
        this.query = "SELECT * FROM person WHERE type_person = ?";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setInt(1,type);
            this.rs = this.ps.executeQuery();
        }
        catch(SQLException ex){ex.printStackTrace();}

        return this.rs;
    }

    public void removePerson(String email, int type_person){
        this.query = "{call deleteClient(?, ?)}";

        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,email);
            this.ps.setInt(2, type_person);
            this.ps.executeUpdate();
            this.ps.close();
        }
        catch(SQLException ex){ex.printStackTrace();}
    }


    public int login(String email, String senha){
        int type_person = 0;
        this.query = "SELECT * FROM person WHERE email = ? AND password_email = ?";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,email);
            this.ps.setString(2,senha);
            this.rs = this.ps.executeQuery();

            if ( !this.rs.next()){System.out.println("Login ou senha incorretos");}

            else{type_person = rs.getInt("type_person");}
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return type_person;
    }
    public ResultSet getPerson(String email){
        this.query = "SELECT * FROM person WHERE email=?";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,email);
            this.rs = this.ps.executeQuery();
        }
        catch(SQLException ex){ex.printStackTrace();}
        return this.rs;
    }
    public void editPersonString(String prev_value, String value, String COLUMN){
        this.query = "UPDATE person SET ?=? WHERE ?=?";
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
        this.query = "UPDATE person SET ?=? WHERE ?=?";
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


    public void valuesQuery(Person p, int type_person){
        try{
            this.ps.setString(1,p.getCpf());
            this.ps.setString(2,p.getFirst_name());
            this.ps.setString(3,p.getEmail());
            this.ps.setString(4,p.getPassword_email());
            this.ps.setInt(5,type_person);
            this.ps.setString(6,p.getLast_name());
            this.ps.setDate(7, Date.valueOf(p.getBirthdate()));
            this.ps.setString(8,p.getCellphone());
            this.ps.setString(9,p.getCity());
            this.ps.setString(10,p.getState());
            this.ps.setString(11,p.getCountry());
            this.ps.setString(12,p.getAdress());
            this.ps.setInt(13,p.getNumber_address());
        }
        catch(SQLException ex){ex.printStackTrace();}
    }
}
