package com.example.hplaptop.apidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {



    Call<EmployeeResponse> call;
    Retrofit retrofit;
    ApiInterface api;
    RecyclerView recyclerView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.btn_submit1);
        String baseUrl="http://kirancreators.com/";

        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setVisibility(View.GONE);

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        //creating the api interface
        api = retrofit.create(ApiInterface.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call = api.listEmployee("Emplist");
                
                
                call.enqueue(new Callback<EmployeeResponse>() {
                    @Override
                    public void onResponse(Call<EmployeeResponse> call, Response<EmployeeResponse> response) {
                        EmployeeResponse model2 = response.body();
                        
                        Log.d("TAG","success : "+ model2.success);
                        Toast.makeText(MainActivity.this, model2.success, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<EmployeeResponse> call, Throwable t) {

                    }
                });
            }
        });

    }
}
