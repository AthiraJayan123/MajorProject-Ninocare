package com.syntax.ninocare.AshaWorker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
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
import com.syntax.ninocare.User.User_View_FoodDetails;

import java.util.HashMap;
import java.util.Map;

public class Asha_View_Users extends AppCompatActivity {


    ListView userList;
    String   id[],users[],hnumber[];
    String listcontent[]={"No users"};


    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asha__view__users);
        userList=(ListView) findViewById(R.id.asha_view_userList);

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                userid=id[i];
                Intent newpage=new Intent(getApplicationContext(),Asha_view_users_indetail.class);
                newpage.putExtra("userid",userid);
                startActivity(newpage);
            }
        });
        vollygetallusers();
    }
    public void vollygetallusers()
    {
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
                    if (!response.isEmpty()) {
                        String ar[] = response.trim().split("#");
                        id = ar[0].split(":");
                        String userhno[] = ar[1].split(":");
//                        users = userhno[0].split(":");
//                        hnumber = userhno[1].split(":");



                           ArrayAdapter add1 = new ArrayAdapter(Asha_View_Users.this, android.R.layout.simple_list_item_1, userhno);
                           userList.setAdapter(add1);

                    } else {
                        //table.removeAllViews();

//                        /listcontent = response.split("#");
                        ArrayAdapter add = new ArrayAdapter(Asha_View_Users.this, android.R.layout.simple_list_item_1, listcontent);
                        userList.setAdapter(add);
                    }


                } else {
                    ArrayAdapter add = new ArrayAdapter(Asha_View_Users.this, android.R.layout.simple_list_item_1, listcontent);
                    userList.setAdapter(add);


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
                final String uid = prefs.getString("logid", "No logid");//"No name defined" is the default value.
                map.put("key", "AshaViewUsersDetails");
                map.put("aid", uid);

                return map;
            }
        };
        queue.add(request);
    }


}
