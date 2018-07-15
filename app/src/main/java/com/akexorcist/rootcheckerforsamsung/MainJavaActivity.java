package com.akexorcist.rootcheckerforsamsung;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.samsung.android.knox.EnterpriseDeviceManager;
import com.scottyab.rootbeer.RootBeer;

public class MainJavaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isKnoxNotSupported() || isDeviceRooted()) {
            Toast.makeText(this, "Not Samsung device or rooted device detected!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Knox API available and not rooted device.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isKnoxNotSupported() {
        try {
            EnterpriseDeviceManager.getAPILevel();
            return false;
        } catch (RuntimeException ignored) {
        }
        return true;
    }

    private boolean isDeviceRooted() {
        return new RootBeer(this).isRooted();
    }
}
