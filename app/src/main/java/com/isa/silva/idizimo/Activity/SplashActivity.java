package com.isa.silva.idizimo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.isa.silva.idizimo.R;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.push.Push;

import static com.isa.silva.idizimo.Utils.Constants.APP_KEY;

public class SplashActivity  extends Activity {
    // Timer da splash screen
    private static int SPLASH_TIME_OUT = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash);


        Push.enableFirebaseAnalytics(getApplication());
        AppCenter.start(getApplication(), APP_KEY,
                Analytics.class, Crashes.class, Push.class);
        new Handler().postDelayed(new Runnable() {
            /*
             * Exibindo splash com um timer.
             */
            @Override
            public void run() {

                Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(i);

                // Fecha esta activity
                finish();

            }
        }, SPLASH_TIME_OUT);
    }
}
