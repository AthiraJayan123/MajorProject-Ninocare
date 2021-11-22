package com.syntax.ninocare.User;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.syntax.ninocare.AshaWorker.Asha_View_GovProjects;
import com.syntax.ninocare.Commom.Common;
import com.syntax.ninocare.Commom.FoodNotAdapter;
import com.syntax.ninocare.Commom.NotificationBean;
import com.syntax.ninocare.Commom.ProgramNotAdapter;
import com.syntax.ninocare.Login;
import com.syntax.ninocare.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RadioGroup rg_not_type;
    RadioButton rb_food,rb_program,r1;
    ArrayList<NotificationBean> arrayList;
    ArrayList<NotificationBean> arrayList1;
    FoodNotAdapter adpater;
    ProgramNotAdapter pr_adpater;
    ListView foodnot_listt, program_list;
    String arr[], id[], data[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        arrayList = new ArrayList<NotificationBean>();
        arrayList1 = new ArrayList<NotificationBean>();
        adpater = new FoodNotAdapter(UserHome.this, arrayList);
        pr_adpater = new ProgramNotAdapter(UserHome.this, arrayList1);

        foodnot_listt = (ListView) findViewById(R.id.foodnot_listt);
        program_list = (ListView) findViewById(R.id.programnot_listt);

        rg_not_type=(RadioGroup)findViewById(R.id.user_not_type);
        rb_food=(RadioButton)findViewById(R.id.user_not_radio_food);
        rb_program=(RadioButton)findViewById(R.id.user_not_radio_program);

        getFoodNotification();

        rg_not_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id=rg_not_type.getCheckedRadioButtonId();
                r1=(RadioButton)findViewById(id);
                String statuscarry=r1.getText().toString().trim();
                if(statuscarry.equals("food")){
                    Toast.makeText(UserHome.this, "food", Toast.LENGTH_SHORT).show();
                    arrayList1.clear();
                    arrayList.clear();
                    program_list.setAdapter(null);
                    getFoodNotification();
                    arrayList1.clear();

                }
                else{
                    arrayList1.clear();
                    arrayList.clear();
                    foodnot_listt.setAdapter(null);
                    getProgramNotification();
                    arrayList.clear();
                }
            }
        });
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        getFoodNotification();
//        getProgramNotification();
        NotificationDetails();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_user_logout) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_fooddetails) {

            startActivity(new Intent(getApplicationContext(), User_View_FoodDetails.class));
        } else if (id == R.id.nav_vaccinationdetails) {
            startActivity(new Intent(getApplicationContext(), User_View_VaccinatDetails.class));

        } else if (id == R.id.nav_panchayathPrograms) {
            startActivity(new Intent(getApplicationContext(), User_View_PanchayathPrograms.class));

        } else if (id == R.id.nav_user_profile) {
            startActivity(new Intent(getApplicationContext(), User_Profile.class));

        }else if (id == R.id.nav_usergovProject) {
           startActivity(new Intent(getApplicationContext(), Asha_View_GovProjects.class));

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getFoodNotification() {

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
//                    String[]arr=response.trim().split("#");
////                    ArrayAdapter a=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arr);
////                    transport_list.setAdapter(a);

                    Gson gson = new Gson();
                    NotificationBean[] beans = gson.fromJson(response, NotificationBean[].class);

                    for (NotificationBean ListBean : beans) {
                        NotificationBean bean = new NotificationBean();
                        bean.setFoodnot_id(ListBean.getFoodnot_id());
                        bean.setFood_name(ListBean.getFood_name());
                        bean.setFood_date(ListBean.getFood_date());
                        bean.setFood_decription(ListBean.getFood_decription());

                        arrayList.add(bean);
                        foodnot_listt.setAdapter(adpater);
                    }

                } else {
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
                final String uid = prefs.getString("logid", "No logid");//"No name defined" is the default value.
                map.put("key", "getFoodNotification");
                map.put("uid", uid);
                return map;
            }
        };
        queue.add(request);
    }

    public void getProgramNotification() {

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
//                    String[]arr=response.trim().split("#");
////                    ArrayAdapter a=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arr);
////                    transport_list.setAdapter(a);

                    Gson gson = new Gson();
                    NotificationBean[] beans = gson.fromJson(response, NotificationBean[].class);

                    for (NotificationBean ListBean : beans) {
                        NotificationBean bean = new NotificationBean();
                        bean.setProgram_id(ListBean.getProgram_id());
                        bean.setProgram_name(ListBean.getProgram_name());
                        bean.setProgram_date(ListBean.getProgram_date());
                        bean.setProgram_location(ListBean.getProgram_location());
                        bean.setProgram_time(ListBean.getProgram_time());
                        bean.setProgram_description(ListBean.getProgram_description());

                        arrayList1.add(bean);
                        program_list.setAdapter(pr_adpater);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "ERROR No date found !", Toast.LENGTH_LONG).show();
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
                map.put("key", "getProgramNotification");
                map.put("uid", uid);
                return map;
            }
        };
        queue.add(request);
    }

    public void NotificationDetails() {

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
                    arr = response.split("%");
                    id = arr[0].split(":");
                    data = arr[1].split("#");
//                    Toast.makeText(UserHome.this, "data{1]"+response, Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < id.length; i++) {
//                    Toast.makeText(getApplicationContext(),data[i],Toast.LENGTH_SHORT).show();
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                        builder.setSmallIcon(android.R.drawable.ic_dialog_alert);

                        long[] v = {500, 1000};
                        builder.setVibrate(v);
                        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        builder.setSound(uri);
                        Intent intent = new Intent(getApplicationContext(), NotificationDetails.class);
                        intent.putExtra("not_id", id[i]);
                        intent.putExtra("not_data", data[i]);

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        builder.setContentIntent(pendingIntent);
                        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                        builder.setContentTitle("Vaccination Notification");
                        builder.setContentText("conducting programs in your area. read the notifications please");
                        builder.setSubText("Tap to view the details.");
                        builder.setPriority(Notification.PRIORITY_MAX);

                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                        // Will display the notification in the notification bar
                        notificationManager.notify(1, builder.build());

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR No date found !", Toast.LENGTH_LONG).show();
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
                map.put("key", "NotificationDetails");
                map.put("uid", uid);
                return map;
            }
        };
        queue.add(request);
    }
}
