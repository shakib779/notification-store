package com.codeartists.notificationstore

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS
import androidx.appcompat.app.AppCompatActivity
import com.codeartists.notificationstore.feature.provider.activity.ProviderListActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}