package com.akexorcist.rootcheckerforsamsung

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.samsung.android.knox.EnterpriseDeviceManager
import com.scottyab.rootbeer.RootBeer

class MainKotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isKnoxNotSupported() || isDeviceRooted()) {
            Toast.makeText(this, "Not Samsung device or rooted device detected!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Knox API available and not rooted device.", Toast.LENGTH_SHORT).show()
        }
    }

    // Root checking by  Samsung Knox API
    private fun isKnoxNotSupported(): Boolean {
        try {
            EnterpriseDeviceManager.getAPILevel()
            return false
        } catch (ignored: RuntimeException) {
        }
        return true
    }

    // Root checking by RootBeer library
    private fun isDeviceRooted(): Boolean = RootBeer(this).isRooted
}
