package model;

public class Customer {
    int idcus;
    String name;
    String address;
    int tel;

    public Customer() {
    }

    public Customer(int idcus, String name, String address, int tel) {
        this.idcus = idcus;
        this.name = name;
        this.address = address;
        this.tel = tel;
    }

    public int getIdcus() {
        return idcus;
    }

    public void setIdcus(int idcus) {
        this.idcus = idcus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
    
    
    
}
