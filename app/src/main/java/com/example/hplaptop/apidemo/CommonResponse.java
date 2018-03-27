package com.example.hplaptop.apidemo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jeetendra.achtani on 22-01-2018.
 */

public class CommonResponse {

    @SerializedName("success")
    public String success;

    @SerializedName("details")
    private ArrayList<Details> details;




}

class Details {

    public Details() {
        name = "";

    }

    @SerializedName("name")

    private String name;






    public String getName() {
        return name;
    }

}
