package com.example.hplaptop.apidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.R.attr.key;
import static android.R.attr.name;

public class ActDetails extends AppCompatActivity {



    TextView tvName,tvPhone,tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_details);
        tvName=findViewById(R.id.tvName);
        tvPhone=findViewById(R.id.tvPhone);
        tvAddress=findViewById(R.id.tvAddress);
        Intent intent = getIntent();

       String name = (intent.getStringExtra ("Name"));
       String phone = (intent.getStringExtra ("Phone"));
       String address = (intent.getStringExtra ("Address"));
        tvName.setText(name.toString());
        tvPhone.setText(phone.toString());
        tvAddress.setText(address.toString());


    }
}
