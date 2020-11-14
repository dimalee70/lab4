package kz.lab4.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kz.lab4.presentation.ui.MainActivity
import timber.log.Timber

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate")
        startActivity(MainActivity.newIntent(this))
        finish()
    }
}