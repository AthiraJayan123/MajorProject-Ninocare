package com.syntax.ninocare.AshaWorker;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.syntax.ninocare.Commom.Common;
import com.syntax.ninocare.R;

import java.util.HashMap;
import java.util.Map;

public class Asha_view_users_indetail extends AppCompatActivity {

    TextView hnum,name,age,carrystatus,address,phone,email,userid;
    Button btnRemove,ChangeToMother;
    String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asha_view_users_indetail);

        hnum=(TextView)findViewById(R.id.asha_view_houseno);
        name=(TextView)findViewById(R.id.asha_view_username);
        age=(TextView)findViewById(R.id.asha_view_age);
        carrystatus=(TextView)findViewById(R.id.asha_view_carryingstatus);
        address=(TextView)findViewById(R.id.asha_view_useraddress);
        phone=(TextView)findViewById(R.id.asha_view_userphn);
        email=(TextView)findViewById(R.id.asha_view_useremail);
        userid=(TextView)findViewById(R.id.asha_view_userid);
        btnRemove=(Button)findViewById(R.id.asha_view_userremovebtn);
        UID= getIntent().getStringExtra("userid");

        Toast.makeText(getApplicationContext(), "user id is "+UID, Toast.LENGTH_SHORT).show();

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Asha_view_users_indetail.this);
                alertDialogBuilder.setMessage(" Do you want to delete user??!!");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                ashaDeleteUser();
                                Toast.makeText(Asha_view_users_indetail.this, "phew Phew..!", Toast.LENGTH_SHORT).show();
                            }
                        });
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });
        getUserDetails();

    }
    public void ashaDeleteUser()
    {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {

                  startActivity(new Intent(getApplicationContext(),Asha_View_Users.class));




                } else {


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
                map.put("key", "ashaDeleteUser");
                map.put("uid", UID.trim());


                return map;
            }
        };
        queue.add(request);
    }

    public void getUserDetails()
    {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {

                        String ar[] = response.split(":");
                        hnum.setText(ar[1]);
                        name.setText(ar[2]);
                        age.setText(ar[3]);
                        carrystatus.setText(ar[4]);
                        address.setText(ar[5]);
                        phone.setText(ar[6]);
                        email.setText(ar[7]);
                        userid.setText(ar[0]);

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
                map.put("key", "getUserDetails");
                map.put("uid", UID.trim());


                return map;
            }
        };
        queue.add(request);
    }


}
