package com.syntax.ninocare.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.syntax.ninocare.AshaWorker.AshaHome;
import com.syntax.ninocare.Commom.Common;
import com.syntax.ninocare.R;

import java.util.HashMap;
import java.util.Map;

public class NotificationDetails extends AppCompatActivity {
    TextView Notdetails;
    String id,data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);
        Notdetails=(TextView)findViewById(R.id.notification_deatails);

        id=getIntent().getStringExtra("not_id");
        data=getIntent().getStringExtra("not_data");
        Notdetails.setText(data);
        updateNotifStatusTable();
        Toast.makeText(getApplicationContext(), "deatail id="+id, Toast.LENGTH_SHORT).show();


    }


    public void updateNotifStatusTable(){
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******",response);
                if(!response.trim().equals("failed"))
                {
                    Toast.makeText(getApplicationContext(), "Readed Successfully !", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "registeration failed !", Toast.LENGTH_LONG).show();
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
                map.put("key","UpdateNotiStatus");
                map.put("aid",uid);
                map.put("vid",id);

                return map;
            }
        };
        queue.add(request);
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),UserHome.class));
    }
}

