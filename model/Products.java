package model;

public class Products {
    private String name_product;
    private String description_product;
    private double price;
    private int quantity;
    private String name_supplier;

    public Products(String name_product, String description_product,
                    double price, int quantity, String name_supplier){
        this.name_product = name_product;
        this.description_product = description_product;
        this.price = price;
        this.quantity = quantity;
        this.name_supplier = name_supplier;
    }
    public String getName_product(){
        return name_product;
    }
    public String getDescription_product(){return description_product;}
    public double getPrice(){return this.price;}
    public int getQuantity(){return this.quantity;}
    public String getName_supplier(){return name_supplier;}
}
