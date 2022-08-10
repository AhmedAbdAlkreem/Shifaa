package com.example.shifaa;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.net.URI;

public class User {

    private String tupeOfDrag;
    private String saleOfDrag;
    private Integer imgDrag;
    public  Uri draw ;

    public User(String tupeOfDrag, String saleOfDrag, Integer imgDrag) {
        this.tupeOfDrag = tupeOfDrag;
        this.saleOfDrag = saleOfDrag;
        this.imgDrag = imgDrag;
    }

    public User(String tupeOfDrag, String saleOfDrag, Uri draw) {
        this.tupeOfDrag = tupeOfDrag;
        this.saleOfDrag = saleOfDrag;
        this.draw = draw;
    }



    public String getTupeOfDrag() {
        return tupeOfDrag;
    }

    public void setTupeOfDrag(String tupeOfDrag) {
        this.tupeOfDrag = tupeOfDrag;
    }

    public String getSaleOfDrag() {
        return saleOfDrag;
    }

    public void setSaleOfDrag(String saleOfDrag) {
        this.saleOfDrag = saleOfDrag;
    }

    public Integer getImgDrag() {
        return imgDrag;
    }

    public void setImgDrag(Integer imgDrag) {
        this.imgDrag = imgDrag;
    }

}
