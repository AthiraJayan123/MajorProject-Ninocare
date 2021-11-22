package com.syntax.ninocare.User;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.syntax.ninocare.Commom.Common;
import com.syntax.ninocare.Commom.VacciFoodAdapter;
import com.syntax.ninocare.Commom.vaccinationViewBean;

import com.syntax.ninocare.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User_View_VaccinatDetails extends AppCompatActivity {

    ListView vaccilist;
    Spinner vacciusertype;
    String USERTYPE;
    String userList[]={"Mother","Child"};
    ArrayList<vaccinationViewBean> arrayList;
    VacciFoodAdapter adpater;
    String listcontent[]={"No Details Found"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__view__vaccinat_details);
//customcode start
        arrayList = new ArrayList<vaccinationViewBean>();
        adpater = new VacciFoodAdapter(User_View_VaccinatDetails.this, arrayList);
        vaccilist=(ListView)findViewById(R.id.user_view_vacciList2);
        vacciusertype=(Spinner)findViewById(R.id.user_view_usertype) ;
        USERTYPE=userList[0];
        volly_getvacciDetails();

        ArrayAdapter arr=new ArrayAdapter(User_View_VaccinatDetails.this,android.R.layout.simple_spinner_dropdown_item,userList);
        vacciusertype.setAdapter(arr);
        vacciusertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                arrayList.clear();
                vaccilist.setAdapter(null);
                if(i==0) {

                    USERTYPE = userList[0];
                    // Toast.makeText(User_View_VaccinatDetails.this, USERTYPE, Toast.LENGTH_SHORT).show();

                }
                else
                {
                    USERTYPE = userList[1];
                }
                volly_getvacciDetails();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void volly_getvacciDetails()
    {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******",response);
                if(!response.trim().equals("failed"))
                {
                        Gson gson = new Gson();
                        vaccinationViewBean[] beans = gson.fromJson(response, vaccinationViewBean[].class);

                        for (vaccinationViewBean ListBean : beans) {
                            vaccinationViewBean bean = new vaccinationViewBean();
                            bean.setVacci_id(ListBean.getVacci_id());
                            bean.setVacci_name(ListBean.getVacci_name());
                            bean.setVacci_details(ListBean.getVacci_details());

                            arrayList.add(bean);
                            vaccilist.setAdapter(adpater);
                        }
//                        listcontent=response.trim().split("#");
//                        ArrayAdapter add=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,listcontent);
//                        vaccilist.setAdapter(add);



                }
                else
                {
//                    ArrayAdapter arr1=new ArrayAdapter(User_View_VaccinatDetails.this, android.R.layout.simple_list_item_1,listcontent);
//                    vaccilist.setAdapter(arr1);
                    Toast.makeText(getApplicationContext(), "ERROR No food found !", Toast.LENGTH_LONG).show();


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
                map.put("key","UserViewVacciDetails");
                map.put("uid",uid);
                 map.put("usertype",USERTYPE.trim());

                return map;
            }
        };
        queue.add(request);
    }

}
