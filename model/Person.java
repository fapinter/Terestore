package model;

import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
}
