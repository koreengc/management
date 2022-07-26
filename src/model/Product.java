package model;

public class Product {
    int idproduct;
    int idsupplier;
    int idcategory;
    String name;
    String unit;
    int price;
    String status;

    public Product() {
    }

    public Product(int idproduct, int idsupplier, int idcategory, String name, String unit, int price, String status) {
        this.idproduct = idproduct;
        this.idsupplier = idsupplier;
        this.idcategory = idcategory;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.status = status;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public int getIdsupplier() {
        return idsupplier;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public int getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }
    
    
}
