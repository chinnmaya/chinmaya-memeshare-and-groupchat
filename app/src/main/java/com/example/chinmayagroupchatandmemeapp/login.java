package com.example.chinmayagroupchatandmemeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

public class login extends AppCompatActivity {
    EditText email,password;
    TextView sign;
    Button login;

    String str_mail,str_pass;
    String urll="https://three-masted-forks.000webhostapp.com/chinmaya_login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        sign=findViewById(R.id.signup);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,MainActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("")){
                    Toast.makeText(login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }
                else if(password.getText().toString().equals("")){
                    Toast.makeText(login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }
                else{


                    final ProgressDialog progressDialog = new ProgressDialog(login.this);
                    progressDialog.setMessage("Please Wait..");

                    progressDialog.show();

                    str_mail = email.getText().toString().trim();
                    str_pass = password.getText().toString().trim();


                    StringRequest request = new StringRequest(Request.Method.POST, urll, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

                            if(response.equalsIgnoreCase("login")){

                                email.setText("");
                                password.setText("");
                                startActivity(new Intent(getApplicationContext(),memeshow.class));

                                Toast.makeText(login.this, response, Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(login.this, response, Toast.LENGTH_SHORT).show();
                            }

                        }
                    },new Response.ErrorListener(){

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(login.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("email",str_mail);
                            params.put("password",str_pass);
                            return params;

                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(login.this);
                    requestQueue.add(request);





                }
            }

        });
    }
}