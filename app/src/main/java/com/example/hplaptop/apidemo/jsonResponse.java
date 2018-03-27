package com.example.hplaptop.apidemo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hplaptop on 28-03-2018.
 */

class GetDetailsData implements Serializable {

    @SerializedName("details")

    private ArrayList<Roles> details;

    @SerializedName("success")
    private String success;

    public GetDetailsData() {
        this.details = new ArrayList<>();
        this.success = "";
    }

    public ArrayList<Roles> getUserRoles() {
        return details;
    }

    public String getMessage() {
        return success;
    }

    public class Roles {

        public Roles() {
            name = "";
            mobile_no = "";
            address = "";

        }

        @SerializedName("name")
        private String name;

        @SerializedName("mobile_no")
        private String mobile_no;

  @SerializedName("address")
        private String address;


        public String getName() {
            return name;
        }
            public String getMobile() {
            return mobile_no;
        }
            public String getAddress() {
            return address;
        }

    }
}