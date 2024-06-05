package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private String bdUsuario;
    private String bdSenha;
    private String bdHost;

    private static Conexao conexao;
    private Connection conn;
    private Conexao(){
        this.bdUsuario = "root";
        this.bdSenha = "2810leticia";
        this.bdHost = "jdbc:mysql://127.0.0.1:3306/terestore?useSSL=false";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(bdHost,bdUsuario,bdSenha);

        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static Conexao getConexao(){
        if (conexao == null) {
            conexao = new Conexao();
        }
        return conexao;
    }
    public Connection getConnection(){
        return this.conn;
    }

}
