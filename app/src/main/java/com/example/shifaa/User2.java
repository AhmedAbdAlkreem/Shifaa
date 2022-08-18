package com.example.shifaa;

import android.net.Uri;

public class User2<Button> {

    private String tupeOfDrag;
    private String saleOfDrag;
    private String mImageUri;

    public User2(){

    }

    public User2(String tupeOfDrag, String saleOfDrag, String mImageUri) {
        if (tupeOfDrag.trim().equals("")) {
            tupeOfDrag = "No Name of Drag";
        } else if (saleOfDrag.trim().equals("")) {
            saleOfDrag = "No Name of Drag";
        }

        this.tupeOfDrag = tupeOfDrag;
        this.saleOfDrag = saleOfDrag;
        this.mImageUri = mImageUri;
    }


    public String getTupeOfDrag1() {
        return tupeOfDrag;
    }

    public void setTupeOfDrag1(String tupeOfDrag) {
        this.tupeOfDrag = tupeOfDrag;
    }

    public String getSaleOfDrag1() {
        return saleOfDrag;
    }

    public void setSaleOfDrag1(String saleOfDrag) {
        this.saleOfDrag = saleOfDrag;
    }

    public String getImgUri1() {
        return mImageUri;
    }

    public void setImgUri1(String imgDrag) {
        this.mImageUri = imgDrag;
    }

}

