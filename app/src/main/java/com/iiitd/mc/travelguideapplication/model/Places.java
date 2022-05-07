package com.iiitd.mc.travelguideapplication.model;

public class Places {
    int Id;
    String URL;
    String placeName;

//    public Places(int imageId, String imgUrl, String placeName) {
//        this.imageId = imageId;
//        this.imgUrl = imgUrl;
//        this.placeName = placeName;
//    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}
