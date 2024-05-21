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

    public void insertPerson(Person p, int type_person){
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
        this.query = "SELECT * FROM person WHERE cpf=?";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,email);
            this.rs = this.ps.executeQuery();
        }
        catch(SQLException ex){ex.printStackTrace();}
        return this.rs;
    }

    public void editPersonString(String CPF, String value, int COLUMN){
        if      (COLUMN == 1){this.query = "UPDATE person SET cpf=? WHERE cpf = ?";}
        else if (COLUMN == 2){this.query = "UPDATE person SET first_name=? WHERE cpf = ?";}
        else if (COLUMN == 3){this.query = "UPDATE person SET email=? WHERE cpf = ?";}
        else if (COLUMN == 4){this.query = "UPDATE person SET password_email=? WHERE cpf = ?";}
        else if (COLUMN == 5){this.query = "UPDATE person SET last_name=? WHERE cpf = ?";}
        else if (COLUMN == 6){this.query = "UPDATE person SET cellphone=? WHERE cpf = ?";}
        else if (COLUMN == 7){this.query = "UPDATE person SET city=? WHERE cpf = ?";}
        else if (COLUMN == 8){this.query = "UPDATE person SET state=? WHERE cpf = ?";}
        else if (COLUMN == 9){this.query = "UPDATE person SET country=? WHERE cpf = ?";}
        else if (COLUMN == 10){this.query = "UPDATE person SET address=? WHERE cpf = ?";}
        else{
            System.out.println("Coluna não identificada");
            return;
        }

        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,value);
            this.ps.setString(2,CPF);
            this.ps.executeUpdate();
            this.ps.close();
        } catch(SQLException ex){ex.printStackTrace();}
    }
    public void editPersonInt(String CPF, int value, int COLUMN){
        if (COLUMN == 1){this.query = "UPDATE person SET type_person= ? WHERE cpf=?";}
        else if (COLUMN == 2){this.query = "UPDATE person SET number_address= ? WHERE cpf=?";}
        else{
            System.out.println("Coluna não identificada");
        }
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setInt(1,value);
            this.ps.setString(2,CPF);
            this.ps.executeUpdate();
            this.ps.close();
        } catch(SQLException ex){ex.printStackTrace();}
    }
    public void editPersonDate(String CPF, String value){
        this.query = "UPDATE person SET birthdate= ? WHERE cpf = ?";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setDate(1,Date.valueOf(value));
            this.ps.setString(2,CPF);
            this.ps.executeUpdate();
            this.ps.close();
        } catch(SQLException ex){ex.printStackTrace();}
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
