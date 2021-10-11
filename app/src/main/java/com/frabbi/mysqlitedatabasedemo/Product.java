package com.frabbi.mysqlitedatabasedemo;

public class Product {
    private int _id;
    private String p_name;
    private String p_desc;
    private double price;

    public Product() {
    }

    public Product(int _id, String p_name, String p_desc, double price) {
        this._id = _id;
        this.p_name = p_name;
        this.p_desc = p_desc;
        this.price = price;
    }

    public Product(String p_name, String p_desc, double price) {
        this.p_name = p_name;
        this.p_desc = p_desc;
        this.price = price;
    }
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_desc() {
        return p_desc;
    }

    public void setP_desc(String p_desc) {
        this.p_desc = p_desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
