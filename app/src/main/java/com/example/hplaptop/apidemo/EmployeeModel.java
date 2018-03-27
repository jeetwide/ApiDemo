package com.example.hplaptop.apidemo;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;

/**
 * Created by jeetendra.achtani on 22-01-2018.
 */

public class EmployeeModel {

    @SerializedName("details")
    public Array details;

    @SerializedName("emp_id")
    public String emp_id;

    @SerializedName("name")
    public String name;

    @SerializedName("email_id")
    public String email_id;


    @SerializedName("mobile_no")
    public String mobile_no;

    @SerializedName("location")
    public String location;


    @SerializedName("gender")
    public String gender;

    @SerializedName("age")
    public String age;
}
