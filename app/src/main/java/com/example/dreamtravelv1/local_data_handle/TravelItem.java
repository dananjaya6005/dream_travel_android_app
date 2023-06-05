package com.example.dreamtravelv1.local_data_handle;


public class TravelItem {
    private String AGADivision;
    private String Address;
    private String District;
    private String Name;
    private String PS_MC_UC;

    public TravelItem(String AGADivision, String Address, String District, String Name, String PS_MC_UC) {
        this.AGADivision = AGADivision;
        this.Address = Address;
        this.District = District;
        this.Name = Name;
        this.PS_MC_UC = PS_MC_UC;
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

    public String getPS_MC_UC() {
        return PS_MC_UC;
    }
}
