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

    public void insertAdmin(Person p){
        this.query = "INSERT INTO person(cpf, first_name, email, password_email, type_person, register_date) VALUES (?,?,?,?,?, CURDATE())";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,p.getCpf());
            this.ps.setString(2, p.getFirst_name());
            this.ps.setString(3,p.getEmail());
            this.ps.setString(4, p.getPassword_email());
            this.ps.setInt(5, p.getType_person());
            System.out.println("rodou aqui");
            this.ps.executeUpdate();
            this.ps.close();
        }
        catch(SQLException ex){ex.printStackTrace();}

    }
    public void insertPerson(Person p){
        this.query = "INSERT INTO person VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?, CURDATE())";

        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            valuesQuery(p);
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

    public void removeSalesman(String email, int type_person){
        this.query = "DELETE FROM person WHERE email = ? AND type_person = ?";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1, email);
            this.ps.setInt(2,type_person);
            this.ps.executeUpdate();
            this.ps.close();
        }
        catch(SQLException ex){ex.printStackTrace();}
    }
    public void removeClient(String email, int type_person){
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
        this.query = "SELECT * FROM person WHERE BINARY email  = ? AND BINARY password_email  = ?";
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
    public String getCPF(String email){
        String cpf = "";
        this.query = "SELECT cpf FROM person WHERE BINARY email=? AND type_person = 2";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,email);
            this.rs = this.ps.executeQuery();
            while(rs.next()){
                cpf = rs.getString("cpf");
            }
        }
        catch(SQLException ex){ex.printStackTrace();}
        return cpf;
    }

    public void editPersonString(String CPF, String value, int COLUMN, int type_person){
        if      (COLUMN == 1){this.query = "UPDATE person SET cpf=? WHERE cpf = ?  AND type_person=?";}
        else if (COLUMN == 2){this.query = "UPDATE person SET first_name=? WHERE cpf = ? AND type_person=?";}
        else if (COLUMN == 3){this.query = "UPDATE person SET email=? WHERE cpf = ? AND type_person=?";}
        else if (COLUMN == 4){this.query = "UPDATE person SET password_email=? WHERE cpf = ? AND type_person=?";}
        else if (COLUMN == 5){this.query = "UPDATE person SET last_name=? WHERE cpf = ? AND type_person=?";}
        else if (COLUMN == 7){this.query = "UPDATE person SET cellphone=? WHERE cpf = ? AND type_person=?";}
        else if (COLUMN == 8){this.query = "UPDATE person SET city=? WHERE cpf = ? AND type_person=?";}
        else if (COLUMN == 9){this.query = "UPDATE person SET state=? WHERE cpf = ? AND type_person=?";}
        else if (COLUMN == 10){this.query = "UPDATE person SET country=? WHERE cpf = ? AND type_person=?";}
        else if (COLUMN == 11){this.query = "UPDATE person SET address=? WHERE cpf = ? AND type_person=?";}
        else{
            System.out.println("Coluna não identificada");
            return;
        }

        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setString(1,value);
            this.ps.setString(2,CPF);
            this.ps.setInt(3, type_person);
            this.ps.executeUpdate();
            this.ps.close();
        } catch(SQLException ex){ex.printStackTrace();}
    }
    public void editPersonInt(String CPF, int value, int type_person){
        this.query = "UPDATE person SET number_address= ? WHERE cpf=? AND type_person=?";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setInt(1,value);
            this.ps.setString(2,CPF);
            this.ps.setInt(3, type_person);
            this.ps.executeUpdate();
            this.ps.close();
        } catch(SQLException ex){ex.printStackTrace();}
    }
    public void editPersonDate(String CPF, String value, int type_person){
        this.query = "UPDATE person SET birthdate= ? WHERE cpf = ? AND type_person = ?";
        try{
            this.ps = conexao.getConnection().prepareStatement(this.query);
            this.ps.setDate(1,Date.valueOf(value));
            this.ps.setString(2,CPF);
            this.ps.setInt(3,type_person);
            this.ps.executeUpdate();
            this.ps.close();
        } catch(SQLException ex){ex.printStackTrace();}
    }

    public void valuesQuery(Person p){
        try{
            this.ps.setString(1,p.getCpf());
            this.ps.setString(2,p.getFirst_name());
            this.ps.setString(3,p.getEmail());
            this.ps.setString(4,p.getPassword_email());
            this.ps.setInt(5,p.getType_person());
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
