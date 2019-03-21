package com.example.solar_energy.model;

import com.example.solar_energy.network.Config;

public class Solar_Item {
    String _id;
    String image;
    String name;
    String company;
    String type;
    String price;
    String percent;
    String range;
    String appearance;

    public String get_id() {
        return this._id;
    }
    public String getImage() {
        return this.image;
    }
    public String getCompany(){
        return this.company;
    }
    public String getName() {
        return this.name;
    }
    public String getType() { return this.type;}
    public String getPrice() { return this.price; }
    public String getPercent() { return this.percent; }
    public String getRange() { return this.range; }
    public String getAppearance() { return this.appearance; }

    public Solar_Item(String _id, String image, String name, String company, String type,
                      String price, String range, String appearance, String percent) {
        this._id = _id;
        this.company = company;
        this.image = Config.MAIN_URL+Config.GET_IMG +image + ".jpg";
        this.name = name;
        this.type = type;
        this.price = price;
        this.range = range;
        this.appearance = appearance;
        this.percent = percent;
    }
}
