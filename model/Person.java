package model;

public class Person {

    private String cpf;
    private String first_name;
    private String email;
    private String password_email;
    private int type_person; // 1: admin, 2: salesman, 3: client
    private String last_name;
    private String birthdate; // "yyyy-mm-dd"
    private String cellphone;
    private String city;
    private String state;
    private String country;
    private String address;
    private int number_address;

    //Construtor utilizado para registrar o admin
    public Person(String cpf, String first_name, String email, String password_email, int type_person){
        this.cpf = cpf;
        this.first_name = first_name;
        this.email = email;
        this.password_email = password_email;
        this.type_person = type_person;
    }
    public Person(String cpf, String first_name, String email, String password_email, int type_person,
    String last_name, String birthdate, String cellphone, String city, String state, String country,
    String address, int number_address){
        this(cpf, first_name, email, password_email, type_person);
        this.last_name = last_name;
        this.birthdate = birthdate;
        this.cellphone = cellphone;
        this.city = city;
        this.state = state;
        this.country = country;
        this.address = address;
        this.number_address = number_address;
    }
    public String getCpf(){return this.cpf;}
    public String getFirst_name(){return this.first_name;}
    public String getEmail(){return this.email;}
    public String getPassword_email(){return this.password_email;}
    public int getType_person(){return this.type_person;}
    public String getLast_name(){return this.last_name;}
    public String getBirthdate(){return this.birthdate;}
    public String getCellphone(){return this.cellphone;}
    public String getCity(){return this.city;}
    public String getState(){return this.state;}
    public String getCountry(){return this.country;}
    public String getAdress(){return this.address;}
    public int getNumber_address(){return this.number_address;}
}
