package com.example.hplaptop.apidemo;

import android.app.ListActivity;
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


                        if(model2.success.equalsIgnoreCase("true")){
                            recyclerView.setVisibility(View.VISIBLE);

                            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(MainActivity.this,model2.data);
                            recyclerView.setAdapter(recyclerAdapter);

                        }
                    }

                    @Override
                    public void onFailure(Call<EmployeeResponse> call, Throwable t) {

                    }
                });
            }
        });

    }
    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        Context ctx;
        public ArrayList<SurveyModel> data;
        public RecyclerAdapter(Context ctx, ArrayList<SurveyModel> temdata) {
            this.ctx=ctx;
            this.data=temdata;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(ctx);
            View view=inflater.inflate(R.layout.list_item,null);


            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            SurveyModel surveyModel;

            SurveyModel product= data.get(position);
            holder.question_order.setText("question_order ="+product.question_order);
            holder.options.setText("Options ="+product.options);
            holder.question_id.setText("Question_Id = "+product.question_id);
            holder.survey_title.setText("Survey_Title ="+product.survey_title);
            holder.survey_id.setText("Survey_ID ="+product.survey_id);


        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView survey_id,question,options,question_id,survey_title,question_order;

            public ViewHolder(View itemView) {
                super(itemView);

                survey_id=itemView.findViewById(R.id.tv_survey_id);
                options=itemView.findViewById(R.id.tv_options);
                question=itemView.findViewById(R.id.tv_question);
                question_id=itemView.findViewById(R.id.tv_question_id);
                survey_title=itemView.findViewById(R.id.tv_survey_title);
                question_order=itemView.findViewById(R.id.tv_question_order);
            }


        }
    }

}
