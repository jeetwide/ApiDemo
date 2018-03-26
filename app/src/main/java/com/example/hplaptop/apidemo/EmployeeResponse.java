package com.example.hplaptop.apidemo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jeetendra.achtani on 22-01-2018.
 */

public class EmployeeResponse extends CommonResponse {


    @SerializedName("data")
    public ArrayList<EmployeeModel> data;

}
