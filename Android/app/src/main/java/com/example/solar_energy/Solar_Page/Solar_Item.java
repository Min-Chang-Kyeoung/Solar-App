package com.example.solar_energy.Solar_Page;

public class Solar_Item {
    String _id;
    String image;
    String title;
    String company;

    String get_id() {
        return this._id;
    }
    String getImage() {
        return this.image;
    }
    String getCompany(){
        return this.company;
    }
    String getTitle() {
        return this.title;
    }

    Solar_Item(String _id, String image, String title, String company) {
        this._id = _id;
        this.company = company;
        this.image = image + ".jpg";
        this.title = title;
    }
}
