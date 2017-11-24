package com.example.windows10now.muathe24h.model;

/**
 * Created by Windows 10 Now on 11/14/2017.
 */

public class Card {
    private String namePrice;
    private int price;

    public Card() {
    }

    public Card(String namePrice, int price) {
        this.namePrice = namePrice;
        this.price = price;
    }

    public Card(String namePrice, int price, String nameHomeNetWork, int imgLogo) {
        this.namePrice = namePrice;
        this.price = price;
    }

    public String getNamePrice() {
        return namePrice;
    }

    public void setNamePrice(String namePrice) {
        this.namePrice = namePrice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
