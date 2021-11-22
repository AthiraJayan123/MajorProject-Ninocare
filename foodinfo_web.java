package com.syntax.ninocare.User;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.syntax.ninocare.R;

public class foodinfo_web extends AppCompatActivity {

    WebView foodweb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodinfo_web);
        foodweb=(WebView)findViewById(R.id.foodwebview);
        String url="https://www.motherandbaby.co.uk/lifestyle-and-celebs/food/healthy-eating-tips-and-advice/the-best-foods-to-eat-at-every-stage-of-pregnancy-and-post-birth";
        foodweb.getSettings().setLoadsImagesAutomatically(true);
        foodweb.getSettings().setJavaScriptEnabled(true);
        foodweb.getSettings().setUseWideViewPort(true);
        foodweb.setInitialScale(1);
        foodweb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        foodweb.loadUrl(url);

        foodweb.setWebViewClient(new MyBrowser());
    }

}
