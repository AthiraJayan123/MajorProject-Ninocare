package com.syntax.ninocare.User;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by user on 10/29/2018.
 */

public class MyBrowser extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
