package com.example.solar_energy.Solar_Page;

import com.example.solar_energy.network.Config;

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
        this.image = Config.MAIN_URL+Config.GET_IMG +image + ".jpg";
        this.title = title;
    }
}
