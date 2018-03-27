package com.example.hplaptop.apidemo;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {



    Call<GetDetailsData> call;
    Retrofit retrofit;
    ApiInterface api;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ArrayList<GetDetailsData.Roles> arrayList;
    Button btn;
    TextView tvUU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUU=findViewById(R.id.tvUU);
        btn = findViewById(R.id.btn_submit1);
        String baseUrl = "http://kirancreators.com/";
        tvUU.setVisibility(View.GONE);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
      //  recyclerView.setVisibility(View.GONE);

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)

                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        //creating the api interface
        api = retrofit.create(ApiInterface.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                tvUU.setVisibility(View.VISIBLE);
                JsonObject Obj = new JsonObject();
                Obj.addProperty("action", "Emplist");
                call = api.listEmployee(Obj);


                call.enqueue(new Callback<GetDetailsData>() {
                    @Override

                    public void onResponse(Call<GetDetailsData> call, Response<GetDetailsData> response) {

                        //String test = response.body();

                        GetDetailsData model2 = response.body();
                        //  Log.e("locale info", "mUserProfileData = " + response.body().toString());
                        // Toast.makeText(MainActivity.this, response.body().getUserRoles().toString(), Toast.LENGTH_SHORT).show();
                         arrayList = response.body().getUserRoles();
                        if (arrayList != null) {
                            // Log.i(LOG_TAG, arrayList.get(0).getName());
                           /* Toast.makeText(MainActivity.this, arrayList.get(0).getName(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, arrayList.get(1).getName(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, arrayList.get(2).getName(), Toast.LENGTH_SHORT).show();
*/
                            for (int i = 0; i < arrayList.size(); i++) {
                                Log.d("TAG", "ArrayLIST : " + arrayList.get(i).getName());
                                Log.d("TAG", "ArrayLIST : " + arrayList.get(i).getMobile());
                                Log.d("TAG", "ArrayLIST : " + arrayList.get(i).getAddress());
                            }
                            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(MainActivity.this,arrayList);
                            recyclerView.setAdapter(recyclerAdapter);

                        }
                        //Log.d("TAG", "success : " + model2.success);
                        // Log.d("TAG", "success : " + model2.);
                        //Toast.makeText(MainActivity.this, model2.success, Toast.LENGTH_SHORT).show();
                        // Toast.makeText(MainActivity.this,, Toast.LENGTH_SHORT).show();


                        //  if (model2.success.equalsIgnoreCase("true")) {
                        //recyclerView.setVisibility(View.VISIBLE

                        //RecyclerAdapter recyclerAdapter = new RecyclerAdapter(MainActivity.this,model2.data);
                        //recyclerView.setAdapter(recyclerAdapter);

                        // }
                    }

                    @Override
                    public void onFailure(Call<GetDetailsData> call, Throwable t) {

                    }

                });
            }
        });




    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        Context ctx;
        ArrayList<GetDetailsData.Roles> arrayList;

        public RecyclerAdapter(Context ctx, ArrayList<GetDetailsData.Roles> arrayList) {

            this.ctx = ctx;
            this.arrayList=arrayList;

        }


        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view = inflater.inflate(R.layout.list_item, null);


            return new ViewHolder(view);


        }

        @Override
        public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
            //arrayList = response.body().getUserRoles();
      final  GetDetailsData.Roles i=arrayList.get(position);
           // EmployeeModel product = data.get(position);
            holder.tv_name.setText((CharSequence) i.getName());

            holder.tv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, ActDetails.class);
                    intent.putExtra("Name", i.getName());
                    intent.putExtra("Phone", i.getMobile());
                    intent.putExtra("Address", i.getAddress());
                   startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_name;
            public ViewHolder(View itemView) {
                super(itemView);
                tv_name = itemView.findViewById(R.id.tv_name);
            }
        }
    }
}
