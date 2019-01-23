package com.example.solar_energy.Solar_Page;

public class Solar_Item {
    int image;
    String title;
    String company;

    int getImage() {
        return this.image;
    }

    String getCompany(){
        return this.company;
    }

    String getTitle() {
        return this.title;
    }

    Solar_Item(int image, String title, String company) {
        this.company = company;
        this.image = image;
        this.title = title;
    }
}
