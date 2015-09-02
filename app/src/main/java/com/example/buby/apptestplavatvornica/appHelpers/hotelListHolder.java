package com.example.buby.apptestplavatvornica.appHelpers;

/**
 * Created by Reaper on 29.8.2015..
 */
public class hotelListHolder {
    private String naslov, adresaA, adresaB;

    public hotelListHolder() {
    }

    public hotelListHolder(String naslov, Integer image, String adresaA, String adresaB) {
        this.naslov = naslov;
        this.adresaA = adresaA;
        this.adresaB = adresaB;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getAdresaA() {
        return adresaA;
    }

    public void setAdresaA(String adresaA) {
        this.adresaA = adresaA;
    }

    public String getAdresaB() {
        return adresaB;
    }

    public void setAdresaB(String adresaB) {
        this.adresaB = adresaB;
    }


}
