package com.example.dreamtravelv1.local_data_handle;

public class RestaurantItem {

    private String AGADivision;
    private String Address;
    private String District;
    private String Name;
    private String Grade;

    public RestaurantItem(String AGADivision, String Address, String District, String Name, String Grade) {
        this.AGADivision = AGADivision;
        this.Address = Address;
        this.District = District;
        this.Name = Name;
        this.Grade = Grade;
    }

    public String getAGADivision() {
        return AGADivision;
    }

    public String getAddress() {
        return Address;
    }

    public String getDistrict() {
        return District;
    }

    public String getName() {
        return Name;
    }

    public String getGrade() {
        return Grade;
    }

}
