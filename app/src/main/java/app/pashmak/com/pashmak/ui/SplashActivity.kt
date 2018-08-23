package app.pashmak.com.pashmak.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.databinding.ActivitySplashBinding

class SplashActivity: AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)

        val binding : ActivitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.setLifecycleOwner(this)
    }
}