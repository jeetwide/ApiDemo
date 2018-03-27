package com.example.hplaptop.apidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.R.attr.key;
import static android.R.attr.name;

public class ActDetails extends AppCompatActivity {



    TextView tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_details);
        tt=findViewById(R.id.tt);
        Intent intent = getIntent();
       String name[] = (intent.getStringArrayExtra ("Phone"));
        tt.setText(name.toString());


    }
}
