package model;

public class Sales {
    private int id_sale;
    private String name_client;
    private int active_client;
    private String name_product;
    private double price_product;
    private int quantity_product;
    private String payment_method;
    public Sales(int id_sale, String name_client, int active_client, String name_product,
                 double price_product, int quantity_product, String payment_method){
        this.id_sale = id_sale;
        this.name_client = name_client;
        this.active_client = active_client;
        this.name_product = name_product;
        this.price_product = price_product;
        this.quantity_product = quantity_product;
        this.payment_method = payment_method;
    }
    public int getId_sale(){return id_sale;}
    public String getName_client(){return name_client;}
    public int getActive_client(){return active_client;}
    public String getName_product(){return name_product;}
    public double getPrice_product(){return price_product;}
    public int getQuantity_product(){return quantity_product;}
    public String getPayment_method(){return payment_method;}
}
