package com.syntax.ninocare.User;

import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.syntax.ninocare.Commom.Base64;
import com.syntax.ninocare.Commom.Common;
import com.syntax.ninocare.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class User_Profile extends AppCompatActivity {

    ImageView profile_pic;
    TextView hnumber,name,age,carring,address,phone,email,profile_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        profile_pic=(ImageView)findViewById(R.id.profile_pic);
        hnumber=(TextView)findViewById(R.id.profile_hnumber);
        name=(TextView)findViewById(R.id.profile_name);
        age=(TextView)findViewById(R.id.profile_age);
        carring=(TextView)findViewById(R.id.profile_carring_status);
        address=(TextView)findViewById(R.id.profile_address);
        phone=(TextView)findViewById(R.id.profile_phone);
        email=(TextView)findViewById(R.id.profile_email);
        profile_type=(TextView)findViewById(R.id.profile_type);
        getProfile();
    }


    public void getProfile()
    {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {

                    String ar[] = response.split(":");
                    String base = ar[8];
                    try {
                        byte[] imageAsBytes = Base64.decode(base.getBytes());

                        profile_pic.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
                    } catch (IOException e) {

                        e.printStackTrace();
                    }

                    hnumber.setText("House Number        :  "+ar[1]);
                    name.setText(ar[2]);
                    age.setText("Age                             :  "+ar[3]);
                    if(ar[4].equals("null")){
                     profile_type.setText("( Mother )");

                    }else{
                        profile_type.setText("( Carrying )");
                        carring.setVisibility(View.VISIBLE);
                        carring.setText("Carrying Status       :  "+ar[4]+"month");
                    }
                    address.setText("Address                     :  "+ar[5]);
                    phone.setText("Phone                         :  "+ar[6]);
                    email.setText("Email                           :  "+ar[7]);


                } else {

                    Toast.makeText(getApplicationContext(), "my error :" , Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "my error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                SharedPreferences prefs = getSharedPreferences("SharedData", MODE_PRIVATE);
                final   String uid = prefs.getString("logid", "No logid");//"No name defined" is the default value.
                map.put("key", "getProfile");
                map.put("uid",uid);


                return map;
            }
        };
        queue.add(request);
    }
}
