package com.syntax.ninocare;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.syntax.ninocare.User.UserHome;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText username,password;
    TextView tvreg;
    Button btnlog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.SEND_SMS,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
        };

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }


        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        username=(EditText)findViewById(R.id.login_edt_email);
        password=(EditText)findViewById(R.id.login_edt_pass);
        btnlog=(Button)findViewById(R.id.btnlog);
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volly_call_login();
            }
        });


    }
    public void volly_call_login()
    {

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******",response);
                if(!response.trim().equals("failed"))
                {
                    String data=response;
                    String arr[]=data.trim().split(":");


//                    Toast.makeText(getApplicationContext(), "Success="+id, Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor editor = getSharedPreferences("SharedData", MODE_PRIVATE).edit();
                    editor.putString("logid", arr[0]);
                    editor.putString("type", arr[1]);
                    editor.commit();

                    if(arr[1].equals("user")) {
                        Intent i = new Intent(Login.this, UserHome.class);
                        startActivity(i);
                    }
                    else if(arr[1].equals("asha")){
                        Intent i = new Intent(Login.this, AshaHome.class);
                        startActivity(i);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "invalid username or password !", Toast.LENGTH_LONG).show();
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
//                SharedPreferences sp=getSharedPreferences("booking_info", Context.MODE_PRIVATE);
                map.put("key","login");
                map.put("uname",username.getText().toString().trim());
                map.put("pass",password.getText().toString().trim());



                return map;
            }
        };
        queue.add(request);
    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}
