import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation {

    public static int validationIntMenu(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        boolean valid = false;

        while (!valid) {
            try {
                option = sc.nextInt();
                if (option >= min && option <= max) {
                    valid = true;
                } else {
                    System.out.print("Opção inválida. Por favor, insira um número entre " + min + " e " + max + ".");
                }
            }
            catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Por favor, insira um número inteiro entre "+min+"e"+max+": ");
                sc.next();
            }
        }

        return option;
    }

    public static int validationInt() {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        boolean valid = false;

        while (!valid) {
            try {
                option = sc.nextInt();
                valid = true;
            }
            catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Por favor, insira um número inteiro: ");
                sc.next();
            }
        }

        return option;
    }

    public static String validationString(Scanner sc) {
        String input = "";

        while (input.isEmpty() || !input.matches("[\\p{L}\\s@._-]+")) {
            input = sc.nextLine();
            if (!input.matches("[\\p{L}\\s@*._-]+")) {
                System.out.print("Entrada inválida. Por favor, insira apenas letras, espaços: ");
            }
        }

        return input;
    }


    public static double validationDouble(Scanner sc) {
        double value = 0.0;
        boolean valid = false;

        while (!valid) {
            try {
                String input = sc.nextLine();
                value = Double.parseDouble(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor, insira um valor numérico: ");
            }
        }

        return value;
    }

    public static String validateStringInt(Scanner sc) {
        String input = "";

        while (input.isEmpty() || !input.matches("\\d*(\\.\\d+)?")) {
            input = sc.nextLine().trim();
            if (!input.matches("\\d*(\\.\\d+)?")) {
                System.out.print("Entrada inválida. Por favor, insira um número válido: ");
            }
        }
        return input;
    }
}
