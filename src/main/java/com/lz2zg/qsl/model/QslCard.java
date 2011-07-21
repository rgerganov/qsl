package com.lz2zg.qsl.model;


public class QslCard {

    long id;
    String callsign;
    String frontImageUrl;
    String backImageUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFrontImageUrl() {
        return frontImageUrl;
    }
    
    public String getFrontThumbUrl() {
        return getThumbName(frontImageUrl);
    }

    public void setFrontImageUrl(String frontImageUrl) {
        this.frontImageUrl = frontImageUrl;
    }

    public String getBackImageUrl() {
        return backImageUrl;
    }

    public String getBackThumbUrl() {
        return getThumbName(backImageUrl);
    }
    
    public void setBackImageUrl(String backImageUrl) {
        this.backImageUrl = backImageUrl;
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }
    
    public static String getThumbName(String name) {
        int dotIndex = name.lastIndexOf(".");
        if (dotIndex >=0 ) {
            return name.substring(0, dotIndex) + "-thumb" + name.substring(dotIndex);
        } else {
            return name + "-thumb";
        }        
    }
}
