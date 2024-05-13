import dao.PersonDAO;
import model.Person;
import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        PersonDAO pDAO = new PersonDAO();
        int type_menu = 0;
        boolean initialMenu = true;
        while(initialMenu) {
            Person p1 = new Person("25632763064", "Amilton", "amiltonasci@gmail.com", "1234",3,"Nascimento", "2002-04-21",
                    "41994572302", "Curitiba", "Paraná", "Brasil", "Rua Desembargador Motta", 324);
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite o email para remover");
            String email = sc.nextLine();


            pDAO.removePerson(email, 3);
            System.out.println("rodou");
            //pDAO.insertPerson(p1, 3);

        }
    }
}
