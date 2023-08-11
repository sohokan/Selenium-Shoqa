package com.ti.seleniumpompagespom;

public class ShoppingItems {

    public String clothe_name;
    public String clothe_type;
    public float clothe_price;
    public float clothe_lowestprice;
    public String clothe_icon;
    public int page_number_icon;


    public ShoppingItems(String clothe_name, String clothe_type, float clothe_price,
                         float clothe_lowestprice, String clothe_icon, int page_number_icon) {
        this.clothe_name = clothe_name;
        this.clothe_type = clothe_type;
        this.clothe_price = clothe_price;
        this.clothe_lowestprice = clothe_lowestprice;
        this.clothe_icon = clothe_icon;
        this.page_number_icon = page_number_icon;
    }

    public String getClothe_name() {
        return clothe_name;
    }

    public void setClothe_name(String clothe_name) {
        this.clothe_name = clothe_name;
    }

    public String getClothe_type() {
        return clothe_type;
    }

    public void setClothe_type(String clothe_type) {
        this.clothe_type = clothe_type;
    }

    public float getClothe_price() {
        return clothe_price;
    }

    public void setClothe_price(float clothe_price) {
        this.clothe_price = clothe_price;
    }

    public float getClothe_lowestprice() {
        return clothe_lowestprice;
    }

    public void setClothe_lowestprice(float clothe_lowestprice) {
        this.clothe_lowestprice = clothe_lowestprice;
    }

    public String getClothe_icon() {
        return clothe_icon;
    }

    public void setClothe_icon(String clothe_icon) {
        this.clothe_icon = clothe_icon;
    }

    public int getPage_number_icon() {
        return page_number_icon;
    }

    public void setPage_number_icon(int page_number_icon) {
        this.page_number_icon = page_number_icon;
    }
}



