package com.example.internetconnectivitycheck;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Initialize Connectivity Manager
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get Active Network Information
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Check Network Status
        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()){
            // When internet is inactive

            // Initialize Dialog
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_network);
            dialog.setCanceledOnTouchOutside(false);

            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            // Set Transparent Background
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            // Set Animation
            dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;

            // Initializing Dialog Variable
            Button tryAgain = dialog.findViewById(R.id.dialogNetwork_tryAgainbtn);
            tryAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Call Recreate Method
                    recreate();
                }
            });

            dialog.show();

        } else {
            // When Internet is Active
            webView.loadUrl("https://m.youtube.com");
        }
    }
}
