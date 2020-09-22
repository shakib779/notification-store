package com.codeartists.notificationstore.feature.provider.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeartists.notificationstore.R
import com.codeartists.notificationstore.feature.notification.activity.NotificationListActivity
import com.codeartists.notificationstore.feature.provider.adapter.ProviderListAdapter
import com.codeartists.notificationstore.feature.provider.constant.ProviderConstant.providers
import com.codeartists.notificationstore.shared.AdapterOnClickListener
import com.codeartists.notificationstore.shared.constant.SharedConstant
import com.codeartists.notificationstore.shared.constant.SharedPreferenceConstant.NOTIFICATION_ACCESS_PREFERENCE
import com.codeartists.notificationstore.shared.constant.SharedPreferenceConstant.NOTIFICATION_ACCESS_PREFERENCE_DEFAULT_VALUE
import kotlinx.android.synthetic.main.activity_provider_list.*

class ProviderListActivity : AppCompatActivity(), AdapterOnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_list)

        this.initUI()
    }

    private fun initUI() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences(NOTIFICATION_ACCESS_PREFERENCE, Context.MODE_PRIVATE)
        val showNotificationAccessIntent = sharedPreferences.getBoolean(
            NOTIFICATION_ACCESS_PREFERENCE,
            NOTIFICATION_ACCESS_PREFERENCE_DEFAULT_VALUE
        )
        if (showNotificationAccessIntent) {
            Log.i("CUCODEARTISTS", "SHARED")
            val editor = sharedPreferences.edit()
            editor.putBoolean(NOTIFICATION_ACCESS_PREFERENCE, false)
            editor.apply()

            val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
            startActivity(intent)
        }
        val providerList = providers
        recycler_view_provider.layoutManager =
            LinearLayoutManager(this)
        recycler_view_provider.adapter = ProviderListAdapter(providerList, this)
    }

    override fun onClickItem(position: Int, data: HashMap<String, Any>) {
        val intent = Intent(this, NotificationListActivity::class.java)
        intent.putExtra(SharedConstant.INTENT_EXTRA_PROVIDER_PACKAGE_NAME, data["packageName"].toString())
        intent.putExtra(SharedConstant.INTENT_EXTRA_PROVIDER_NAME, data["name"].toString())
        startActivity(intent)
    }
}