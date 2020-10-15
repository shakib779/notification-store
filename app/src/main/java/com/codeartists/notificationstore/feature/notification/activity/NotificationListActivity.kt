package com.codeartists.notificationstore.feature.notification.activity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.core.view.MotionEventCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.codeartists.notificationstore.R
import com.codeartists.notificationstore.feature.notification.adapter.NotificationListAdapter
import com.codeartists.notificationstore.feature.notification.model.Notification
import com.codeartists.notificationstore.feature.provider.adapter.ProviderListAdapter
import com.codeartists.notificationstore.shared.AdapterOnClickListener
import com.codeartists.notificationstore.shared.constant.SharedConstant
import com.codeartists.notificationstore.shared.db.AppDatabase
import kotlinx.android.synthetic.main.activity_notification_list.*
import kotlinx.android.synthetic.main.activity_provider_list.*
import kotlinx.coroutines.runBlocking

class NotificationListActivity : AppCompatActivity(), AdapterOnClickListener {

    private var notificationList = listOf<Notification>()
    private var idsToDelete = listOf<Int>()
    private val listChange = MutableLiveData<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_list)

        val packageName = intent.getStringExtra(SharedConstant.INTENT_EXTRA_PROVIDER_PACKAGE_NAME)!!
        val name = intent.getStringExtra(SharedConstant.INTENT_EXTRA_PROVIDER_NAME)!!

        buttonDelete.text = "Delete all from ${name}"

        listChange.value = 0

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "notification-db"
        ).allowMainThreadQueries().build()

        runBlocking {

            notificationList = db.notificationDao().findByPackageName(packageName).asReversed()

            idsToDelete = notificationList.map { item -> item.id }

            listChange.value = listChange.value!! + 1
        }

        listChange.observe(this, Observer {
            recycler_view_notification.layoutManager =
                LinearLayoutManager(this)
            recycler_view_notification.adapter = NotificationListAdapter(notificationList, this)
        })

        buttonDelete.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Are you sure you want to delete all notification?")
                .setCancelable(true)
                .setPositiveButton("Yes, Delete") { dialog, id ->
                    runBlocking {
                        db.notificationDao().deleteItems(idsToDelete)

                        notificationList = db.notificationDao().findByPackageName(packageName).asReversed()

                        idsToDelete = notificationList.map { item -> item.id }

                        listChange.value = listChange.value!! + 1
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, id ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }
    }

    override fun onClickItem(position: Int, data: HashMap<String, Any>) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete this notification?")
            .setCancelable(true)
            .setPositiveButton("Yes, Delete") { dialog, id ->
                runBlocking {
                    val db = Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java, "notification-db"
                    ).allowMainThreadQueries().build()


                    db.notificationDao().deleteItems(listOf(data["id"] as Int))

                    notificationList = db.notificationDao().findByPackageName(data["packageName"].toString()).asReversed()

                    idsToDelete = notificationList.map { item -> item.id }

                    listChange.value = listChange.value!! + 1
                }
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
}