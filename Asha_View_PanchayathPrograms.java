package com.syntax.ninocare.AshaWorker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.syntax.ninocare.Commom.Common;
import com.syntax.ninocare.Commom.GovProjectAdapter;
import com.syntax.ninocare.Commom.PanProgramsAdapter;
import com.syntax.ninocare.Commom.vaccinationViewBean;
import com.syntax.ninocare.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Asha_View_PanchayathPrograms extends AppCompatActivity {

    ListView panProgramList;
    ArrayList<vaccinationViewBean> arrayList;
    PanProgramsAdapter adpater;
    String listcontent[]={"Panchayath Programs not available"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asha__view__panchayath_programs);

        arrayList = new ArrayList<vaccinationViewBean>();
        adpater = new PanProgramsAdapter(Asha_View_PanchayathPrograms.this, arrayList);
        panProgramList=(ListView)findViewById(R.id.asha_view_panList);
        volly_getpanPrograms();

    }


    public void volly_getpanPrograms()
    {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******",response);
                if(!response.trim().equals("failed")) {
                    listcontent = response.trim().split("#");

                    // ArrayAdapter arr1 = new ArrayAdapter(Asha_add_childuser.this, android.R.layout.simple_spinner_dropdown_item,name );
//                    ArrayAdapter arr1=new ArrayAdapter(Asha_View_PanchayathPrograms.this,android.R.layout.simple_list_item_1,listcontent);
//                    panProgramList.setAdapter(arr1);

                    Gson gson = new Gson();
                    vaccinationViewBean[] beans = gson.fromJson(response, vaccinationViewBean[].class);

                    for (vaccinationViewBean ListBean : beans) {
                        vaccinationViewBean bean = new vaccinationViewBean();
                        bean.setProgName(ListBean.getProgName());
                        bean.setPanProLoc(ListBean.getPanProLoc());
                        bean.setPanProDate(ListBean.getPanProDate());
                        bean.setProgtime(ListBean.getProgtime());
                        bean.setProgDet(ListBean.getProgDet());
                        arrayList.add(bean);
                        panProgramList.setAdapter(adpater);
                    }
                }
                else
                {
//                    ArrayAdapter arr1=new ArrayAdapter(Asha_View_PanchayathPrograms.this, R.layout.support_simple_spinner_dropdown_item,listcontent);
//                    arr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    panProgramList.setAdapter(arr1);

                    Toast.makeText(getApplicationContext(), " no data !", Toast.LENGTH_LONG).show();
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
                map.put("key","AshaViewPanPrograms");
                map.put("aid",uid);

                return map;
            }
        };
        queue.add(request);
    }

}
