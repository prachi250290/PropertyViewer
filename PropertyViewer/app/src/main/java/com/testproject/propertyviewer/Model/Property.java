package com.testproject.propertyviewer.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prachi on 25/12/16.
 */

public class Property {

    @SerializedName("Project_Name")
    private String name;

    @SerializedName("project_description")
    private String description;

    @SerializedName("Address")
    private String address;

    @SerializedName("Project_Area_Name")
    private String area;

    @SerializedName("Config_Type")
    private String type;

    @SerializedName("Developer_Name")
    private String developerName;

    @SerializedName("Developer_Overview")
    private String developerInfo;

    @SerializedName("Min_Price")
    private String minPrice;

    @SerializedName("Display_Price")
    private String displayPrice;

    @SerializedName("Possession_Text")
    private String possessionText;

    @SerializedName("display_image")
    private String image;

    @SerializedName("developer_logo_file")
    private String developerImage;

    @SerializedName("Map_Latitude")
    private String latitude;

    @SerializedName("Map_Longitude")
    private String longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getDeveloperInfo() {
        return developerInfo;
    }

    public void setDeveloperInfo(String developerInfo) {
        this.developerInfo = developerInfo;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getDisplayPrice() {
        return displayPrice;
    }

    public void setDisplayPrice(String displayPrice) {
        this.displayPrice = displayPrice;
    }

    public String getPossessionText() {
        return possessionText;
    }

    public void setPossessionText(String possessionText) {
        this.possessionText = possessionText;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDeveloperImage() {
        return developerImage;
    }

    public void setDeveloperImage(String developerImage) {
        this.developerImage = developerImage;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
