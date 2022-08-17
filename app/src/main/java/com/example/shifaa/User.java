package com.example.shifaa;

import android.net.Uri;


public class User {

    private String tupeOfDrag;
    private String saleOfDrag;
    private String urlImag;
    private Integer imgDrag;
    public String mImageUri;

    public User() {

    }

    public User(String tupeOfDrag, String saleOfDrag, Integer imgDrag) {
        this.tupeOfDrag = tupeOfDrag;
        this.saleOfDrag = saleOfDrag;
        this.imgDrag = imgDrag;
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

    public String getUrlImage() {
        return urlImag;
    }

    public void seturlImag(String tupeOfDrag) {
        this.urlImag = urlImag;
    }

    public String getmImageUri() {
        return mImageUri;
    }

    public void setmImageUri(String mImageUri) {
        this.mImageUri = mImageUri;
    }

}
