package com.example.hplaptop.apidemo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by jeetendra.achtani on 22-01-2018.
 */

public interface ApiInterface {


  /*  @FormUrlEncoded
    @POST("rest/login")
    Call<LoginResponse> Login(
            @Field("username") String username,
            @Field("password") String email
    );*/

    @FormUrlEncoded
    @POST("api.php")
    Call<EmployeeResponse> listEmployee(
            @Field("emp_id") String emp_id
            //@Field("access_token") String access_token,
           // @Field("survey_id") String survey_id
    );



    //op=login
    // &email=adarsh@eliteinfoworld.com
    // &password=admin@123
    // &platform=1&
    // iostoken=asdasdasda
    // &androidtoken
    // &appmode=2

/*
    @POST("ws.wolfkeeper.php?")
    Call<SignUpResponce> setLogin(
            @Query("op") String op,
            @Query("email") String email,
            @Query("password") String password*/
          /*  @Query("platform") String platform,
            @Query("iostoken") String iostoken,
            @Query("androidtoken") String androidtoken,
            @Query("appmode") String appmode,
            @Query("timezone") String timezone*/
   // );



}
