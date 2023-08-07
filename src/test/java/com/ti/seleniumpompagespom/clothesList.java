package com.ti.seleniumpompagespom;

public class clothesList {

    public String clothe_name;
    public String clothe_type;
    public String clothe_price;
    public String clothe_img;
    public String clothe_icon;
    public String page_number_icon;



    public clothesList(String clothe_name, String clothe_type, String clothe_price,
                       String clothe_img, String clothe_icon, String page_number_icon) {
        this.clothe_name=clothe_name;
        this.clothe_type=clothe_type;
        this.clothe_price=clothe_price;
        this.clothe_img=clothe_img;
        this.clothe_icon=clothe_icon;
        this.page_number_icon=page_number_icon;
    }

    public String getclothe_name() {
        return clothe_name;
    }
    public String getclothe_type() {
        return clothe_type;
    }
    public String getclothe_price() {
        return clothe_price;
    }

    public String getclothe_img () {
        return clothe_img;
    }

    public String getclothe_icon() {
        return clothe_icon;
    }

    public String getpage_number_icon() {
        return page_number_icon;
    }
}

