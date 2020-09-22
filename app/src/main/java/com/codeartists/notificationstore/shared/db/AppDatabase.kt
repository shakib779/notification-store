package com.codeartists.notificationstore.shared.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codeartists.notificationstore.feature.notification.data.dao.NotificationDao
import com.codeartists.notificationstore.feature.notification.model.Notification

@Database(entities = [Notification::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
}