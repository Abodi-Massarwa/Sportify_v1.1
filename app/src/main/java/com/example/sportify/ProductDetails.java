package com.example.sportify;

import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static java.lang.Double.parseDouble;

public class ProductDetails{

    private final String DEF_IMAGE = "https://firebasestorage.googleapis.com/v0/b/rentme-cdf84.appspot.com/o/1575107154720.jpg?alt=media&token=349bb82d-a50f-4736-b5a0-6470031bad0e";
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private String title;
    private String details;
    private String image_url;
    private ImageView image_url1;
    private String condition;
    private String price;
    private String utc;
    private String quantity;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    private String total;


    public ProductDetails(String title, String details, String price, String quantity) {
        this.title = title;
        this.details = details;
        this.quantity=quantity;
        this.price = price;

    }
    public ProductDetails(String title, String details, String condition, String price, String id) {
        this.title = title;
        this.details = details;
        this.condition = condition;
        this.price = price;
        this.utc=id;
    }



    //public String id;
    public String getUtc() {
        return utc;
    }

    public ProductDetails(){

    }
//    public ProductDetails(String title, String details, String condition, String price, String utc, String quantity,String img) {
//        this.title = title;
//        this.details = details;
//        this.condition = condition;
//        this.price = price;
//        this.utc = utc;
//        this.quantity=quantity;
//        this.image_url=img;
//
//        Double x= (Integer.parseInt(quantity.trim())*Double.parseDouble(price.split(":")[1].trim()));
//        this.total=x.toString();
//        Log.d("emt7an",quantity);
//        Log.d("emt7an",price);
//        Log.d("emt7an", total);
//    }
    public ProductDetails(String title, String details, String condition, String price, String utc, String quantity,String img,String total) {
        this.title = title;
        this.details = details;
        this.condition = condition;
        this.price = price;
        this.utc = utc;
        this.quantity=quantity;
        this.image_url=img;

        Double x= (Integer.parseInt(quantity.trim())*Double.parseDouble(price.split(":")[1].trim()));
        total=x.toString();
        this.total=total;
        Log.d("emt7an",quantity);
        Log.d("emt7an",price);
        Log.d("emt7an", total);
    }
//    public ProductDetails(String title, String details, String condition, String price, String utc, String quantity, ImageView img ) {
//        this.title = title;
//        this.details = details;
//        this.condition = condition;
//        this.price = price;
//        this.utc = utc;
//        this.quantity=quantity;
//        this.image_url1=img;
//    }
    public String getQuantity(){return this.quantity;}
    public String getTitle() {
        return title;
    }
    public String getDetails() {
        return details;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getCondition() {
        return condition;
    }

    public String getPrice() {
        return price;
    }

    public void setImage_url(String str){this.image_url=str;}

    @NonNull
    @Override
    public String toString() {
        return "[" + title
                + "," + details
                + "," + image_url
                + "," + condition
                + "," + price
                + "," + utc +","+quantity+ "]";
    }
}