import dao.PersonDAO;
import model.Person;
import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        PersonDAO pDAO = new PersonDAO();
        int type_menu = 0;
        boolean initialMenu = true;
        while(initialMenu){

            Scanner sc = new Scanner(System.in);
            System.out.print("Digite seu Email: ");
            String usuario = sc.nextLine();
            System.out.print("Digite a sua senha: ");
            String senha = sc.nextLine();
            type_menu = pDAO.login(usuario,senha);
            if (type_menu == 1){
                initialMenu = false;
                System.out.println("Bem vindo admin");

            }
            else if (type_menu == 2) {
                initialMenu = false;
                System.out.println("Bem vindo Vendedor");
            }
        }
        switch (type_menu){

        }



    }
}
