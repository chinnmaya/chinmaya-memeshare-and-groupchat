package com.example.chinmayagroupchatandmemeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class homeactivity extends AppCompatActivity {
    EditText type,name;
    ApiInterface apiInterface;
    String typee,namee;
    RecyclerView recyclerView;
    String urlll="https://three-masted-forks.000webhostapp.com/chinmaya_chat.php";
    ImageView send;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);
        type=findViewById(R.id.edtMessage);
        send=findViewById(R.id.imggg);
        name=findViewById(R.id.editTextTextPersonName3);
        recyclerView=findViewById(R.id.recycle);
        apiInterface=Retrofitinstance.getRetrofit().create(ApiInterface.class);
        apiInterface.getposts().enqueue(new Callback<List<PostPojo>>() {
            @Override
            public void onResponse(Call<List<PostPojo>> call, retrofit2.Response<List<PostPojo>> response) {
                if(response.body().size()>0){
                    List<PostPojo>pojoList =response.body();
                    AdapterClass adapterClass=new AdapterClass(pojoList,homeactivity.this);
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(homeactivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapterClass);
                    Toast.makeText(homeactivity.this, "list is not empty", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(homeactivity.this, "list is empty", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<PostPojo>> call, Throwable t) {
                Toast.makeText(homeactivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.getText().toString().equals("")){
                    Toast.makeText(homeactivity.this, "Enter messsage", Toast.LENGTH_SHORT).show();
            }
                else if(name.getText().toString().equals("")) {
                    Toast.makeText(homeactivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                }else {
                    typee=type.getText().toString().trim();
                    namee=name.getText().toString().trim();
                    StringRequest request=new StringRequest(Request.Method.POST, urlll, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            type.setText("");



                            Toast.makeText(homeactivity.this, response, Toast.LENGTH_SHORT).show();

                        }
                        }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {


                            Toast.makeText(homeactivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                    ){


                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params=new HashMap<String, String>();
                            params.put("message",typee);
                            params.put("name",namee);

                            return params;


                        }
                    };
                    RequestQueue requestQueue= Volley.newRequestQueue(homeactivity.this);
                    requestQueue.add(request);




                }

                }
        });



    }
}