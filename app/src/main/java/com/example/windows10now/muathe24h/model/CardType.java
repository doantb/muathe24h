package com.example.windows10now.muathe24h.model;

/**
 * Created by Windows 10 Now on 11/14/2017.
 */

public class CardType extends Card {
    private String nameHomeNetWork;
    private int imgLogo;
    private boolean isWatch = false;

    public CardType(String nameHomeNetWork, int imgLogo) {
        this.nameHomeNetWork = nameHomeNetWork;
        this.imgLogo = imgLogo;
    }

    public CardType(String namePrice, int price, String nameHomeNetWork, int imgLogo,
            boolean isWatch) {
        super(namePrice, price);
        this.nameHomeNetWork = nameHomeNetWork;
        this.imgLogo = imgLogo;
        this.isWatch = isWatch;
    }

    public boolean isWatch() {
        return isWatch;
    }

    public void setWatch(boolean watch) {
        isWatch = watch;
    }

    public String getNameHomeNetWork() {
        return nameHomeNetWork;
    }

    public void setNameHomeNetWork(String nameHomeNetWork) {
        this.nameHomeNetWork = nameHomeNetWork;
    }

    public int getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(int imgLogo) {
        this.imgLogo = imgLogo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        CardType itemCompare = (CardType) obj;
        if (itemCompare.getNameHomeNetWork().equals(this.getNameHomeNetWork())) return true;

        return false;
    }
}
