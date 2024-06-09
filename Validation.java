import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import dao.PersonDAO;
import dao.SupplierDAO;

public class Validation {

    private static ArrayList<String> emails = new ArrayList<>();

    static {
        emails.add("@gmail.com");
        emails.add("@yahoo.com");
        emails.add("@hotmail.com");
        emails.add("@pucpr.edu.br");
        emails.add("@outlook.com");
    }

    public static String validateEmail(Scanner sc, int type_validation) {
        String input = "";
        boolean valid = false;

        while (!valid) {
            input = sc.nextLine().trim();

            if (input.matches(".+@.+\\..+")) {
                for (String email : emails) {
                    if (input.endsWith(email)) {
                        valid = true;
                        break;
                    }
                }
            }

            if (!valid && type_validation == 1) {
                System.out.print("Por favor, insira um endereço de e-mail válido: ");
            } else if (!valid && type_validation == 2) {
                for (String email : emails) {
                    System.out.println("---------------");
                    System.out.println(email);
                    System.out.println("---------------");
                }
                System.out.print("Email não autorizado para o sistema, utilize um dos tipos de email acima: ");
            }
        }
        return input;
    }

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
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Por favor, insira um número inteiro entre " + min + " e " + max + ": ");
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
            } catch (InputMismatchException e) {
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
                System.out.print("Entrada inválida. Por favor, insira apenas letras e espaços: ");
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

    public static String validateDate(Scanner sc) {
        String input = "";
        Pattern pattern = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            input = sc.nextLine().trim();
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                inputFormat.setLenient(false);
                try {
                    java.util.Date date = inputFormat.parse(input);
                    return outputFormat.format(date);
                } catch (ParseException e) {
                    System.out.print("Entrada inválida. Por favor, insira uma data válida (dd/MM/yyyy): ");
                }
            } else {
                System.out.print("Entrada inválida. Por favor, insira uma data no formato dd/MM/yyyy: ");
            }
        }
    }

    public static String validateCPF(Scanner sc, PersonDAO personDAO) {
        String input = "";

        while (true) {
            input = sc.nextLine().trim();
            if (!input.matches("\\d{11}")) {
                System.out.print("CPF inválido. Por favor, insira apenas números com 11 dígitos: ");
            } else if (personDAO.CPFExist(input)) {
                break;
            } else {
                System.out.print("Erro: CPF não existe no banco de dados. Por favor, insira um CPF válido: ");
            }
        }
        return input;
    }

    public static String validateCNPJ(Scanner sc, SupplierDAO supplierDAO) {
        String input = "";

        while (true) {
            input = sc.nextLine().trim();
            if (!input.matches("\\d{14}")) {
                System.out.print("CNPJ inválido. Por favor, insira apenas números com 14 dígitos: ");
            } else if (supplierDAO.CNPJExist(input)) {
                break;
            } else {
                System.out.print("Erro: CNPJ não existe no banco de dados. Por favor, insira um CNPJ válido: ");
            }
        }
        return input;
    }

}