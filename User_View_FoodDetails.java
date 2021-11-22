package com.syntax.ninocare.User;

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
import com.google.gson.Gson;
import com.syntax.ninocare.AshaWorker.Asha_View_PanchayathPrograms;
import com.syntax.ninocare.Commom.Common;
import com.syntax.ninocare.Commom.FoodViewAdapter;
import com.syntax.ninocare.Commom.PanProgramsAdapter;
import com.syntax.ninocare.Commom.vaccinationViewBean;
import com.syntax.ninocare.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.widget.TableRow.LayoutParams;

public class User_View_FoodDetails extends AppCompatActivity {

    ListView foodList;
    public String listcontent[] = {""};
    ArrayList<vaccinationViewBean> arrayList;
    FoodViewAdapter adpater;
    String FOODNa[], FAGE[], FDET[];
    TextView foodmoreInfo;

    TableLayout tl;

    TableRow tr;

    TextView companyTV, valueTV,detTv;


    Spinner foodusertype;
    String USERTYPE;
    String userList[] = {"Mother", "Child"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__view__food_details);
        foodList = (ListView) findViewById(R.id.user_view_foodList);
        foodusertype = (Spinner) findViewById(R.id.user_view_foodusertype);
        foodmoreInfo=(TextView) findViewById(R.id.user_view_foodmoreInfo);
        arrayList = new ArrayList<vaccinationViewBean>();
        adpater = new FoodViewAdapter(User_View_FoodDetails.this, arrayList);
        USERTYPE = "Mother";
        ArrayAdapter arr = new ArrayAdapter(User_View_FoodDetails.this, android.R.layout.simple_spinner_dropdown_item, userList);
        foodusertype.setAdapter(arr);

        volly_getfoodDetails();
        foodmoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),foodinfo_web.class));
            }
        });

        foodusertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                arrayList.clear();
                foodList.setAdapter(null);
                USERTYPE = userList[i];
                volly_getfoodDetails();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }



    public void volly_getfoodDetails() {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
                    if (!response.isEmpty()) {
                        //listcontent=response.split("#");
                        String ar[] = response.trim().split("#");
//                        FOODNa = ar[0].split(":");
//                        String fagdet[] = ar[1].split("@");
//
//                        FAGE = fagdet[0].split(":");
//                        FDET = fagdet[1].split(":");



//                        addData();
//                        ArrayAdapter add1 = new ArrayAdapter(User_View_FoodDetails.this, android.R.layout.simple_list_item_1, ar);
//                        foodList.setAdapter(add1);
                        Gson gson = new Gson();
                        vaccinationViewBean[] beans = gson.fromJson(response, vaccinationViewBean[].class);

                        for (vaccinationViewBean ListBean : beans) {
                            vaccinationViewBean bean = new vaccinationViewBean();
                            bean.setFoodName(ListBean.getFoodName());
                            bean.setFoodPeriod(ListBean.getFoodPeriod());
                            bean.setVacci_details(ListBean.getFoodDetails());
                            arrayList.add(bean);
                            foodList.setAdapter(adpater);
                        }

                    } else {
                            String arr[]={"No Data Available"};
                        ArrayAdapter add = new ArrayAdapter(User_View_FoodDetails.this, android.R.layout.simple_list_item_1, arr);
                        foodList.setAdapter(add);
                    }


                } else {

                    ArrayAdapter arr1 = new ArrayAdapter(User_View_FoodDetails.this, android.R.layout.simple_list_item_1, listcontent);
                    foodList.setAdapter(arr1);
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
                map.put("key", "UserViewFoodDetails");
                map.put("uid", uid);
                map.put("usertype", USERTYPE.trim());

                return map;
            }
        };
        queue.add(request);
    }
}


