package com.example.chinmayagroupchatandmemeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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
import java.util.Map;

public class upload extends AppCompatActivity {
    EditText name,upload;
    Button bupload;
    String str_name,str_upload;
    ImageView back;
    String urlk="https://three-masted-forks.000webhostapp.com/chinmaya_postmeme.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        name=findViewById(R.id.editTextTextPersonName);
        upload=findViewById(R.id.editTextTextPersonName2);
        back=findViewById(R.id.imageView9);
        bupload=findViewById(R.id.button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(upload.this,memeshow.class));
            }
        });
        bupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(upload.this);
                progressDialog.setTitle("please wait........");
                if (name.getText().toString().equals("")) {
                    Toast.makeText(upload.this, "Enter Name", Toast.LENGTH_SHORT).show();
                } else if (upload.getText().toString().equals("")) {
                    Toast.makeText(upload.this, "Enter the link", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.show();
                    str_name=name.getText().toString().trim();
                    str_upload=upload.getText().toString().trim();

                    StringRequest request=new StringRequest(Request.Method.POST, urlk, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            name.setText("");
                            upload.setText("");



                            Toast.makeText(upload.this, response, Toast.LENGTH_SHORT).show();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();

                            Toast.makeText(upload.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                    ){


                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params=new HashMap<String, String>();
                            params.put("name",str_name);
                            params.put("url",str_upload);

                            return params;


                        }
                    };
                    RequestQueue requestQueue= Volley.newRequestQueue(upload.this);
                    requestQueue.add(request);
                }
            }
        });
    }
}
