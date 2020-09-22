package com.codeartists.notificationstore.shared.helper

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.room.Room
import com.codeartists.notificationstore.feature.notification.model.Notification
import com.codeartists.notificationstore.feature.provider.constant.ProviderConstant
import com.codeartists.notificationstore.shared.db.AppDatabase
import kotlinx.coroutines.runBlocking


class NotificationService : NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val providerList = ProviderConstant.providers
        val ignoredTitle = ProviderConstant.ignoredTitle

        if (providerList.any { item -> item.packageName == sbn.packageName.toString() } && !ignoredTitle.contains(
                sbn.notification.extras["android.title"].toString()
            )) {

//            Log.i("CUCODE", sbn.toString())
//            Log.i("CUCODE", sbn.notification.toString())

//            for (key in sbn.notification.extras.keySet()) {
//                Log.i(
//                    "CUCODEARTISTS",
//                    key + "=" + sbn.notification.extras[key].toString()
//                )
//            }

            runBlocking {
                val db = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "notification-db"
                ).allowMainThreadQueries().build()

                db.notificationDao().insertAll(
                    Notification(
                        packageName = sbn.packageName.toString(),
                        title = sbn.notification.extras["android.title"].toString(),
                        body = sbn.notification.extras["android.text"].toString()
                    )
                )
            }
        }
    }
}





//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.title=Shakib.me
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.hiddenConversationTitle=null
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.reduced.images=true
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.subText=null
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.template=android.app.Notification$MessagingStyle
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.showChronometer=false
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.text=📷 Photo
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.progress=0
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.progressMax=0
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.selfDisplayName=You
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.appInfo=ApplicationInfo{9218b8 com.whatsapp}
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.messages=[Landroid.os.Parcelable;@80cc691
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.showWhen=true
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.largeIcon=Icon(typ=BITMAP size=95x95)
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.messagingStyleUser=Bundle[mParcelledData.dataSize=428]
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.messagingUser=android.app.Person@76af2038
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.infoText=null
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.wearable.EXTENSIONS=Bundle[mParcelledData.dataSize=4096]
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.progressIndeterminate=false
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.remoteInputHistory=null
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: last_row_id=42
//2020-09-22 23:34:18.349 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.isGroupConversation=false








//2020-09-22 23:34:18.363 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.title=Shakib.me
//2020-09-22 23:34:18.363 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.reduced.images=true
//2020-09-22 23:34:18.363 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.subText=null
//2020-09-22 23:34:18.363 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.showChronometer=false
//2020-09-22 23:34:18.363 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.people.list=[android.app.Person@68a91133]
//2020-09-22 23:34:18.363 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.text=📷 Photo
//2020-09-22 23:34:18.363 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.progress=0
//2020-09-22 23:34:18.363 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.progressMax=0
//2020-09-22 23:34:18.363 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.appInfo=ApplicationInfo{5a38693 com.whatsapp}
//2020-09-22 23:34:18.363 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.showWhen=true
//2020-09-22 23:34:18.363 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.largeIcon=null
//2020-09-22 23:34:18.363 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.infoText=null
//2020-09-22 23:34:18.363 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.progressIndeterminate=false
//2020-09-22 23:34:18.363 10643-10643/com.codeartists.notificationstore I/CUCODEARTISTS: android.remoteInputHistory=null