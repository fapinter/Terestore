package model;

import java.util.Locale;

public class Supplier {
    private String cnpj;
    private String company_name;
    private String name_person;
    private String email;
    private String password_email;
    private String phone_number;
    private String city;
    private String state;
    private String country;
    private String address;
    private int number_address;

    public Supplier(String cnpj, String company_name, String name_person, String email,
                    String password_email, String phone_number, String city, String state,
                    String country, String address, int number_address){
        this.cnpj = cnpj;
        this.company_name =company_name;
        this.name_person = name_person;
        this.email = email;
        this.password_email = password_email;
        this.phone_number = phone_number;
        this.city = city;
        this.state = state;
        this.country = country;
        this.address = address;
        this.number_address = number_address;
    }

    public String getCnpj() {return cnpj;}
    public String getCompany_name() {return company_name;}
    public String getName_person() {return name_person;}
    public String getEmail() {return email;}
    public String getPassword_email() {return password_email;}
    public String getPhone_number() {return phone_number;}
    public String getCity() {return city;}
    public String getState() {return state;}
    public String getCountry() {return country;}
    public String getAddress() {return address;}
    public int getNumber_address() {return number_address;}
}
